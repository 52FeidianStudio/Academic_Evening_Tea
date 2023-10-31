package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.DeptActivity;
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
        DeptActivity deptActivity = new DeptActivity();
        deptActivity.setActivityId(tblUserActivity.getActivityId());
        deptActivity.setDeptId(tblUserActivity.getDeptId());
        Long resNum = deptActivityMapper.getResNum(deptActivity);
        if(resNum== null){
            return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
        }
        if(resNum==0){
            return 0;
        }
        else{
            deptActivity.setResNum(resNum-1);
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
         if(isEnd==1){
             return tblUserActivityMapper.updateTblUserActivity(tblUserActivity);
         }
         else {
             return 0;
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
