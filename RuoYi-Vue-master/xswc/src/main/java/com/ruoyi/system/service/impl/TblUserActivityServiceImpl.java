package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblUserActivityMapper;
import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.service.ITblUserActivityService;

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
     * 新增【请填写功能名称】
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblUserActivity(TblUserActivity tblUserActivity)
    {
        tblUserActivity.setCreateTime(DateUtils.getNowDate());
        return tblUserActivityMapper.insertTblUserActivity(tblUserActivity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblUserActivity(TblUserActivity tblUserActivity)
    {
        tblUserActivity.setUpdateTime(DateUtils.getNowDate());
        return tblUserActivityMapper.updateTblUserActivity(tblUserActivity);
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
}
