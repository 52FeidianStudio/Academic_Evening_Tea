package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblHead;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public interface ITblHeadService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblHead selectTblHeadById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblHead 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblHead> selectTblHeadList(TblHead tblHead);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblHead 【请填写功能名称】
     * @return 结果
     */
    public int insertTblHead(TblHead tblHead);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblHead 【请填写功能名称】
     * @return 结果
     */
    public int updateTblHead(TblHead tblHead);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTblHeadByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblHeadById(Long id);
}
