package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblHeadMapper;
import com.ruoyi.system.domain.TblHead;
import com.ruoyi.system.service.ITblHeadService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
@Service
public class TblHeadServiceImpl implements ITblHeadService 
{
    @Autowired
    private TblHeadMapper tblHeadMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblHead selectTblHeadById(Long id)
    {
        return tblHeadMapper.selectTblHeadById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblHead 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblHead> selectTblHeadList(TblHead tblHead)
    {
        return tblHeadMapper.selectTblHeadList(tblHead);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblHead 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblHead(TblHead tblHead)
    {
        return tblHeadMapper.insertTblHead(tblHead);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblHead 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblHead(TblHead tblHead)
    {
        return tblHeadMapper.updateTblHead(tblHead);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblHeadByIds(Long[] ids)
    {
        return tblHeadMapper.deleteTblHeadByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblHeadById(Long id)
    {
        return tblHeadMapper.deleteTblHeadById(id);
    }
}
