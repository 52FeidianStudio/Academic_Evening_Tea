package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblActivityMapper;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.service.ITblActivityService;

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

    /**
     * 查询商家发布文章
     * 
     * @param id 商家发布文章主键
     * @return 商家发布文章
     */
    @Override
    public TblActivity selectTblActivityById(Long id)
    {
        return tblActivityMapper.selectTblActivityById(id);
    }

    /**
     * 查询商家发布文章列表
     * 
     * @param tblActivity 商家发布文章
     * @return 商家发布文章
     */
    @Override
    public List<TblActivity> selectTblActivityList(TblActivity tblActivity)
    {
        return tblActivityMapper.selectTblActivityList(tblActivity);
    }

    /**
     * 新增用户发布活动
     * 
     * @param tblActivity 用户发布活动
     * @return 结果
     */
    @Override
    public int insertTblActivity(TblActivity tblActivity)
    {
        return tblActivityMapper.insertTblActivity(tblActivity);
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
        return tblActivityMapper.deleteTblActivityById(id);
    }
}
