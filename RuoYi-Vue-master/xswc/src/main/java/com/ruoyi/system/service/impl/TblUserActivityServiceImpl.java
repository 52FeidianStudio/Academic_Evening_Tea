package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.annotation.updateActivity;
import com.ruoyi.system.annotation.updateUserActivity;
import com.ruoyi.system.aspect.UpdateActivityAspect;
import com.ruoyi.system.constant.ActivityConstant;
import com.ruoyi.system.constant.CreditConstant;
import com.ruoyi.system.constant.ResultConstant;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;

import com.ruoyi.system.utils.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.ITblUserActivityService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblUserActivityServiceImpl implements ITblUserActivityService 
{
    @Autowired
    private TblUserActivityMapper tblUserActivityMapper;

    @Autowired
    private DeptActivityMapper deptActivityMapper;

    @Autowired
    private TblActivityMapper tblActivityMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TblCreditUserMapper tblCreditUserMapper;

    @Autowired
    private RedisLockService redisLockService;
    @Autowired
    private PlatformTransactionManager transactionManager;
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblUserActivity selectTblUserActivityById(Long id)
    {
        return tblUserActivityMapper.selectTblUserActivityById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblUserActivity> selectTblUserActivityList(TblUserActivity tblUserActivity)
    {
        return tblUserActivityMapper.selectTblUsersActivityList(tblUserActivity);
    }

    /**
     * 用户报名活动
     *
     * @param tblUserActivity 用户报名活动
     * @return 结果
     */
    @Override
//    @Transactional
    public int insertTblUserActivity(TblUserActivity tblUserActivity) {
        // 是否报过名
        List<TblUserActivity> tblUserActivities = tblUserActivityMapper.selectTblUserActivityList(tblUserActivity);
        if (tblUserActivities.size() != 0) {
            return -2;
        }
        // 对活动状态更新
        TblActivity pretblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
        updateActivityAspect(pretblActivity);



        String lockKey = "activityLock:" + tblUserActivity.getActivityId();
        String lockValue = UUID.randomUUID().toString(); // 防止锁被误释放

        // 获取 Redis 锁，锁的超时时间为 10 秒
        int retryCount = 0;
        boolean isLocked = redisLockService.tryLock(lockKey, lockValue, 10);
        while(!isLocked&& retryCount < 3){
            retryCount++;
            isLocked=redisLockService.tryLock(lockKey,lockValue,10);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!isLocked) {
//            System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            return -3; // 返回-3表示获取锁失败，活动正在被其他用户处理
        }
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
        try {

            // 判断活动报名是否截止
            TblActivity tblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
            if (tblActivity.getIsClose() == 2) {
                return -1;
            }

            // 得到用户的部门id
            Long userId = SecurityUtils.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            Long deptId = sysUser.getDeptId();

            // 检查用户的学院人数是否有限制
            DeptActivity deptActivity = new DeptActivity();
            deptActivity.setActivityId(tblUserActivity.getActivityId());
            deptActivity.setDeptId(deptId);
            Long resNum = deptActivityMapper.getResNum(deptActivity);

            // 该活动的已经报名人数不能超过总人数
            Long hbNum = tblActivity.getHbNum();
            Long hot = tblActivity.getHot();
            Long res = hot - hbNum;

            // 用户对应的学院没有限制报名人数
            if (res > 0 && resNum == null) {
                tblActivity.setHbNum(hbNum + 1);
                tblActivityMapper.updateTblActivity(tblActivity);
                tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
                transactionManager.commit(transactionStatus);
//                System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111");
                return 1;
            }
            // 学院剩余人数为0 或者活动报名人数达到上限
            if (res <= 0 || resNum == 0) {
                return ResultConstant.ERROR;
            }
            // 用户学院报名人数设有限制
            else {
                deptActivity.setResNum(resNum - 1);
                tblActivity.setHbNum(hbNum + 1);
                tblActivityMapper.updateTblActivity(tblActivity);
                deptActivityMapper.updateDeptActivity(deptActivity);
                tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
                transactionManager.commit(transactionStatus);
//                System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
                return 1;
            }
        } finally {
//             释放锁
            redisLockService.releaseLock(lockKey, lockValue);
        }
    }

    /**
     * 用户报名活动
     * 
     * @param 用户报名活动
     * @return 结果
     */
//    @Override
//    @Transactional
//    public int insertTblUserActivity(TblUserActivity tblUserActivity)
//    {
//        //是否报过名
//        List<TblUserActivity> tblUserActivities = tblUserActivityMapper.selectTblUserActivityList(tblUserActivity);
//        if(tblUserActivities.size()!=0){
//            return -2;
//        }
//        //对活动状态更新
//        TblActivity pretblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
//        updateActivityAspect(pretblActivity);
//        //判断活动报名是否截止
//        TblActivity tblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
//        if(tblActivity.getIsClose()==2){
//            return -1;
//        }
//        //得到用户的部门id
//        Long userId = SecurityUtils.getUserId();
//        SysUser sysUser = sysUserMapper.selectUserById(userId);
//        Long deptId = sysUser.getDeptId();
//        //检查用户的学院人数是否有限制
//        DeptActivity deptActivity = new DeptActivity();
//        deptActivity.setActivityId(tblUserActivity.getActivityId());
//        deptActivity.setDeptId(deptId);
//        Long resNum = deptActivityMapper.getResNum(deptActivity);
//
//        //该活动的已经报名人数不能超过总人数
//        Long hbNum = tblActivity.getHbNum();
//        Long hot = tblActivity.getHot();
//        Long res=hot-hbNum;
//
//        //用户对应的学院没有限制报名人数
//        if(res>0&&resNum== null){
//            tblActivity.setHbNum(hbNum+1);
//            tblActivityMapper.updateTblActivity(tblActivity);
//            return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
//        }
//        //学院剩余人数为0 或者活动报名人数达到上限
//        if(res<=0 || resNum==0){
//            return ResultConstant.ERROR;
//        }
//        //用户学院报名人数设有限制
//        else{
//            deptActivity.setResNum(resNum-1);
//            tblActivity.setHbNum(hbNum+1);
//            tblActivityMapper.updateTblActivity(tblActivity);
//            deptActivityMapper.updateDeptActivity(deptActivity);
//            return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
//        }
//    }
    public void  updateActivityAspect (TblActivity tblActivity){

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 获取活动开始时间
        String time = tblActivity.getLat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String avalibableTimeStr = tblActivity.getStartTime();//获取可以报名的时间
        LocalDateTime avalibableTime = StringUtils.isNull(avalibableTimeStr)?
                LocalDateTime.parse("1999-12-12 00:00:00", formatter)://未来时间不可能到达
                LocalDateTime.parse(avalibableTimeStr, formatter);

        Duration begainEntoll = Duration.between(avalibableTime, now);

        // 将时间字符串转化成时间格式
        LocalDateTime startTime = LocalDateTime.parse(time, formatter);


        // 计算活动开始时间与当前时间的差距
        Duration duration = Duration.between(now , startTime);

        // 定义一个两小时的时长
        Duration twoHours = Duration.ofHours(2);

        //以下为关闭活动操作
        // 如果活动持续时间超过两小时


        if(begainEntoll.compareTo(Duration.ofHours(0)) < 0){
            tblActivity.setIsClose(0);
            tblActivityMapper.updateTblActivity(tblActivity);
            return;
        }else{
            Integer isClose = tblActivity.getIsClose();
            if(isClose == 0) {
                tblActivity.setIsClose(1);
                tblActivityMapper.updateTblActivity(tblActivity);
            }
        }
        if(duration.compareTo(twoHours) <=0){
            // 设置活动为关闭状态
            tblActivity.setIsClose(ActivityConstant.ACTIVITYISCLOSE);

            // 如果活动开始时间早于或等于当前时间，设置活动为已结束状态
            if(startTime.isBefore(now) || startTime.isEqual(now)){
                tblActivity.setIsEnd(ActivityConstant.ACTIVITYISEND);
            }
            // 更新TblActivity记录
            tblActivityMapper.updateTblActivity(tblActivity);
        }
        /*
         * 规则：
         * 如果活动开始后时间超过两小时，则表明活动已经关闭了
         * 如果还没到活动开始时间则优先设置为未开始
         * */

    }

    /**
     * 用户签到
     * 
     * @param tblUserActivity 用户签到
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTblUserActivity(TblUserActivity tblUserActivity)
    {
        //对活动状态更新
        TblActivity pretblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
        updateActivityAspect(pretblActivity);

        Long userId= SecurityUtils.getUserId();
        tblUserActivity.setUserId(userId);
        Long isEnd=tblActivityMapper.getIsEndByActivityId(tblUserActivity.getActivityId());
        Long isStart=tblActivityMapper.getIsStartByActivityId(tblUserActivity.getActivityId());
        TblUserActivity tblUserActivityvo = tblUserActivityMapper.selectTblUserActivityByUA(tblUserActivity);


        if (isStart == 0){
//
//            tblUserActivity.setStatus(ActivityConstant.NOSHOW);
//            tblUserActivityMapper.updateTblUserActivity(tblUserActivity);

            return ActivityConstant.REFISTERISNOTSTART;

        }

        //过了签到时间
        if (isEnd.equals(ActivityConstant.ACTIVITYISEND.longValue())  ){

//            tblUserActivity.setStatus(ActivityConstant.NOSHOW);
//            tblUserActivityMapper.updateTblUserActivity(tblUserActivity);

            return ActivityConstant.REFISTERTIMEOUT;

        }
        //没有报名该活动
        if(tblUserActivityvo ==null ) {
            return  ActivityConstant.NOREGISTERED;
        }
        //重复签到
        if(tblUserActivityvo.getStatus().equals(ActivityConstant.REGISTER)){
            return ActivityConstant.REPESTEDREGISTER;
        }

        //签到成功
        tblUserActivity.setStatus(ActivityConstant.REGISTER);
        tblUserActivityMapper.updateTblUserActivity(tblUserActivity);
        //加积分
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        sysUser.setCredit(sysUser.getCredit().add(new BigDecimal(1)));
        sysUserMapper.updateUser(sysUser);
        //加积分记录
        TblCreditUser tblCreditUser = new TblCreditUser();
        tblCreditUser.setUserId(userId);
        tblCreditUser.setCreditNum(new BigDecimal(1));
        tblCreditUser.setCreditType(CreditConstant.ACTIVITY.longValue());
        tblCreditUserMapper.insertTblCreditUser(tblCreditUser);
        return ActivityConstant.REGISTERSUCCESS;
    }



    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblUserActivityByIds(Long[] ids)
    {
        return tblUserActivityMapper.deleteTblUserActivityByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblUserActivityById(Long id)
    {
        return tblUserActivityMapper.deleteTblUserActivityById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public int deleteTblUserActivityByActivityIds(Long[] ids) {
        return tblUserActivityMapper.deleteTblUserActivityByActivityIds(ids);
    }

    /**
     * 个人中心 我的活动
     * @param tblUserActivity
     * @return
     */
    @Override
    public List<UserActivity> selectMyActivity(TblUserActivity tblUserActivity) {
        List<UserActivity> list=tblUserActivityMapper.selectMyActivity(tblUserActivity);
        return list;
    }
}
