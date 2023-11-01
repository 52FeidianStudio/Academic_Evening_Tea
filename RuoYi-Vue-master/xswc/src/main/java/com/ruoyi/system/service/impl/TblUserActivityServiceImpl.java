package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.constant.ActivityConstant;
import com.ruoyi.system.constant.ResultConstant;
import com.ruoyi.system.domain.DeptActivity;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.mapper.DeptActivityMapper;
import com.ruoyi.system.mapper.TblActivityMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblUserActivityMapper;
import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.service.ITblUserActivityService;
import org.springframework.transaction.annotation.Transactional;

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
        return tblUserActivityMapper.selectTblUserActivityList(tblUserActivity);
    }

    /**
     * 用户报名活动
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTblUserActivity(TblUserActivity tblUserActivity)
    {


        //检查用户的学院人数是否有限制
        DeptActivity deptActivity = new DeptActivity();
        deptActivity.setActivityId(tblUserActivity.getActivityId());
        deptActivity.setDeptId(tblUserActivity.getDeptId());
        Long resNum = deptActivityMapper.getResNum(deptActivity);

        //该活动的已经报名人数不能超过总人数
        TblActivity tblActivity = tblActivityMapper.selectTblActivityById(tblUserActivity.getActivityId());
        Long hbNum = tblActivity.getHbNum();
        Long hot = tblActivity.getHot();
        Long res=hot-hbNum;

        //学院剩余人数为0 或者活动报名人数达到上限
        if(res<=0 || resNum==0){
            return ResultConstant.ERROR;
        }
        //用户对应的学院没有限制报名人数
        if(resNum== null){
            tblActivity.setHbNum(hbNum+1);
            tblActivityMapper.updateTblActivity(tblActivity);
            return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
        }
        //用户学院报名人数设有限制
        else{
            deptActivity.setResNum(resNum-1);
            tblActivity.setHbNum(hbNum+1);
            tblActivityMapper.updateTblActivity(tblActivity);
            deptActivityMapper.updateDeptActivity(deptActivity);
            return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
        }
    }

    /**
     * 用户签到
     * 
     * @param tblUserActivity 用户签到
     * @return 结果
     */
    @Override
    //TODO在报名前扫一遍活动表，保证签到的时候活动没有结束
    public int updateTblUserActivity(TblUserActivity tblUserActivity)
    {
         Long isEnd=tblActivityMapper.getIsEndByActivityId(tblUserActivity.getActivityId());
         if(isEnd.equals(ActivityConstant.ACTIVITYNOTEND) ){
             return tblUserActivityMapper.updateTblUserActivity(tblUserActivity);
         }
         else {
             return ResultConstant.ERROR;
         }
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

    @Override
    public int deleteTblUserActivityByActivityIds(Long[] ids) {
        return tblUserActivityMapper.deleteTblUserActivityByActivityIds(ids);
    }
}
