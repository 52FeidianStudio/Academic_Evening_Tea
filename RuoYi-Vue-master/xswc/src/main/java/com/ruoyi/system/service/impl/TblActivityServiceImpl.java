package com.ruoyi.system.service.impl;

import java.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.system.constant.ActivityConstant;
import com.ruoyi.system.constant.ResultConstant;
import com.ruoyi.system.domain.DeptActivity;
import com.ruoyi.system.domain.DeptNum;
import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.example.HttpPostRequestExample;
import com.ruoyi.system.mapper.DeptActivityMapper;

import com.ruoyi.system.mapper.TblUserActivityMapper;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblActivityMapper;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.service.ITblActivityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商家发布文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblActivityServiceImpl implements ITblActivityService
{
    @Autowired
    private TblActivityMapper tblActivityMapper;

    @Autowired
    private   TblUserActivityMapper tblUserActivityMapper;

    @Autowired
    private DeptActivityMapper deptActivityMapper;




    /**
     *  用户获取发布活动详细信息
     * 
     * @param id  用户发布活动主键
     * @return 商家发布文章
     */
    @Override
    public TblActivity selectTblActivityById(Long id)
    {
        //查询活动详情
        TblActivity tblActivity = tblActivityMapper.selectTblActivityById(id);

        //更新活动状态
        Integer isEnd = tblActivity.getIsEnd();
        if (isEnd!=2)
        {
            //更新活动状态
            UpdateActivityAspect(tblActivity);
        }
        //未登录情况
        if(SecurityUtils.getUserId()==null){
            return tblActivity;
        }
        //登录 情况 判断用户是否报过该活动
        TblUserActivity tblUserActivity = new TblUserActivity();
        tblUserActivity.setActivityId(id);
        tblUserActivity.setUserId(SecurityUtils.getUserId());
        List<TblUserActivity> isApplication = tblUserActivityMapper.selectTblUserActivityList(tblUserActivity);
        tblActivity.setIsApplication(isApplication);

        //展示活动 的学院限制
        DeptActivity deptActivity = new DeptActivity();
        deptActivity.setActivityId(tblActivity.getId());
        tblActivity.setDeptActivities(deptActivityMapper.selectDeptActivityList(deptActivity));

        //展示报名的人信息
        tblUserActivity.setUserId(null);
        List<TblUserActivity> tblUserActivities=tblUserActivityMapper.selectTblUsersActivityList(tblUserActivity);
        tblActivity.setTblUserActivities(tblUserActivities);

//        for (TblUserActivity userActivity : tblUserActivities) {
//            System.out.println("***");
//            System.out.println(userActivity);
//        }
        return tblActivity;
    }

    /**
     * 用户查询活动集合
     *
     * @param tblActivity 用户查询活动
     * @return 用户查询活动集合
     */

    @Override
    public List<TblActivity> selectTblActivityList( TblActivity tblActivity)
    {
        System.out.println(tblActivity.getPageNum());
        System.out.println(tblActivity.getPageSize());
        List<TblActivity>list= tblActivityMapper.selectTblActivityList(tblActivity);
          //查询学院限制情况
        for(TblActivity tblActivity1:list){
            Integer isEnd = tblActivity1.getIsEnd();
            if (isEnd!=2)
            {
                //更新活动状态
                UpdateActivityAspect(tblActivity1);
            }
            DeptActivity deptActivity = new DeptActivity();
            deptActivity.setActivityId(tblActivity1.getId());
            tblActivity1.setDeptActivities(deptActivityMapper.selectDeptActivityList(deptActivity));

        }
        return list;
    }


    public void  UpdateActivityAspect (TblActivity tblActivity){

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
     * 新增用户发布活动
     * 
     * @param tblActivity 用户发布活动
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTblActivity(TblActivity tblActivity)
    {
        tblActivityMapper.insertTblActivity(tblActivity);
        DeptNum[] deptNums=tblActivity.getDeptNums();
        Long acticityId=tblActivity.getId();
        //有学院限制
        if (deptNums!=null){
            for(DeptNum deptNum:deptNums){
                DeptActivity deptActivity = new DeptActivity();
                deptActivity.setActivityId(acticityId);
                deptActivity.setDeptId(deptNum.getDeptId());
                deptActivity.setMaxNum(deptNum.getMaxNum());
                deptActivity.setResNum(deptNum.getMaxNum());
                deptActivityMapper.insertDeptActivity(deptActivity);
            }
        }
        return ResultConstant.SUCEESS;
    }

    /**
     * 修改商家发布文章
     * 
     * @param tblActivity 商家发布文章
     * @return 结果
     */
    @Override
    public int updateTblActivity(TblActivity tblActivity)
    {
        Long activityId = tblActivity.getId();
        TblActivity tblActivityDTO = tblActivityMapper.selectTblActivityById(activityId);
       Integer state= tblActivity.getState();
        //审核

         //通过审核生成二维码
       if (tblActivity.getSigninFilePath()==null&&tblActivity.getState().equals(ActivityConstant.PASS) && !tblActivityDTO.getState() .equals(ActivityConstant.PASS) ) {
//            System.out.println("123456");
           //二维码
            HttpPostRequestExample httpPostRequestExample = new HttpPostRequestExample();
            String accessToken = httpPostRequestExample.postSendAccessToken();
            String applicationFilePath = httpPostRequestExample.postApplication(accessToken,activityId);
            String signinFilePath = httpPostRequestExample.postSignIn(accessToken, activityId);
            tblActivity.setSigninFilePath(signinFilePath);
            tblActivity.setApplicationFilePath(applicationFilePath);

            //活动期数和总活动数
              // 学术晚茶：统计id=2270对应"2024-04-11 19:00:00"时间之后的活动
              // 学术社区:统计id=2266 对应"2024-03-26 10:00:00"时间之后的活动
           String dateSortOne = "2024-04-11 19:00:00";
           String dateSortTwo ="2024-03-26 10:00:00";

           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

           LocalDateTime dateTimeLat = LocalDateTime.parse(tblActivity.getLat(), formatter);
           LocalDateTime dateTimeSortOne = LocalDateTime.parse(dateSortOne, formatter);
           LocalDateTime dateTimeSortTwo = LocalDateTime.parse(dateSortTwo, formatter);

           if (!(dateTimeLat.isAfter(dateTimeSortOne) || dateTimeLat.isEqual(dateTimeSortOne))) {
//               System.out.println(dateTimeLat + " 大于或等于 " + dateTimeSortOne);
               return tblActivityMapper.updateTblActivity(tblActivity);

           } else if(!(dateTimeLat.isAfter(dateTimeSortTwo) || dateTimeLat.isEqual(dateTimeSortTwo))) {
//               System.out.println(dateTimeLat+ " 大于或等于 " + dateTimeSortTwo);
               return tblActivityMapper.updateTblActivity(tblActivity);
           }

           //1查询该活动举办该一年的活动并设置该活动的总期数
           int sum =0;//该年总活动数
           int  rank =0;//新排序
           int totality=0;//该活动对应学术晚茶（社区）的总期数
           if(tblActivity.getSort()==1){//学术晚茶：统计id=2270对应"2024-04-11 19:00:00"时间之后的活动
               sum=tblActivityMapper.countSortOneYear(tblActivity.getLat());//该年总活动数
               totality = tblActivityMapper.countSortOne(tblActivity.getLat())+1;//该活动对应学术晚茶（社区）的总期数=410+这段时间的活动数+1
               tblActivity.setTotality(Long.valueOf( totality));//设置该活动对应学术晚茶（社区）的总期数

               //2024年的学术晚茶id=2270开始记录的第八期，所以2024年的期数记录不一样
               LocalDateTime dateTime = LocalDateTime.parse(tblActivity.getLat(), java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
               if(dateTime.getYear() == 2024){
                   rank=rank+7;
               }
           }else{//学术社区:统计id=2266 对应"2024-03-26 10:00:00"时间之后的活动
               sum=tblActivityMapper.countSortTwoYear(tblActivity.getLat());//该年总活动数
               totality = tblActivityMapper.countSortTwo(tblActivity.getLat())+1;//该活动的总期数=12+这段时间的活动数+1
               tblActivity.setTotality(Long.valueOf( totality));//设置该活动的总期数
           }

           //2查找比该活动举办时间晚的活动（按照举办时间排序从低到高）
           List<TblActivity> tblActivities = tblActivityMapper.countLate(tblActivity.getLat(),tblActivity.getSort());
           //3如果没有比它晚的活动，期数等于该年总活动数+1
           if(tblActivities==null){
               tblActivity.setNum(Long.valueOf(sum+1) );
           }else{
               //4如果有，则计算该活动的期数，并修改比它晚的活动期数和总活动数
               int  tmp = tblActivities.toArray().length;
               rank =rank+sum-tmp+1;
               tblActivity.setNum(Long.valueOf(rank) );
               for(TblActivity pretblActivity:tblActivities){
//                   rank++;
//                   totality++;
                   pretblActivity.setTotality(pretblActivity.getTotality()+1);
                   pretblActivity.setNum(pretblActivity.getNum()+1);
                   tblActivityMapper.updateTblActivity(pretblActivity);
               }
           }

        }

        return tblActivityMapper.updateTblActivity(tblActivity);
    }

    /**
     * 批量删除商家发布文章
     * 
     * @param ids 需要删除的商家发布文章主键
     * @return 结果
     */
    @Override
    public int deleteTblActivityByIds(Long[] ids)
    {
        //TODO 未对期数和总期数进行修改
        return tblActivityMapper.deleteTblActivityByIds(ids);
    }

    /**
     * 删除商家发布文章信息
     * 
     * @param id 商家发布文章主键
     * @return 结果
     */
    @Override
    public int deleteTblActivityById(Long id)
    {
        //TODO 未对期数和总期数进行修改
        return tblActivityMapper.deleteTblActivityById(id);
    }

    /**
     * 用户修改活动
     * @param tblActivity
     * @return
     */
    @Override
    public int editTblActivity(TblActivity tblActivity) {
        Long acticityId=tblActivity.getId();

        //如果修改时间需要重新判断活动报名和结束状态
        tblActivity.setIsClose(0);
        tblActivity.setIsEnd(1);

        //修改学院限制
            //删除原有学院限制
        deptActivityMapper.deleteDeptActivityByActivityId(acticityId);
           //新增修改的学院限制
        DeptNum[] deptNums=tblActivity.getDeptNums();

        //有学院限制
        if (deptNums!=null){
            for(DeptNum deptNum:deptNums){
                DeptActivity deptActivity = new DeptActivity();
                deptActivity.setActivityId(acticityId);
                deptActivity.setDeptId(deptNum.getDeptId());
                deptActivity.setMaxNum(deptNum.getMaxNum());
                deptActivity.setResNum(deptNum.getMaxNum());
                deptActivityMapper.insertDeptActivity(deptActivity);
            }
        }
        //修改活动信息
        tblActivity.setState(1);//修改为待审核
        return tblActivityMapper.updateTblActivity(tblActivity);
    }

//    @Override
//    public int countBySort(Integer sort) {
//        int count=0;
//        if(sort==1){
//            count =tblActivityMapper.countSortOne();
//        }
//        if(sort==2){
//           count = tblActivityMapper.countSortTwo();
//        }
//        return count;
//    }
}
