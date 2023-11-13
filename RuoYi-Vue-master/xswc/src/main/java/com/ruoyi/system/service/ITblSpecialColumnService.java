package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblSpecialColumn;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public interface ITblSpecialColumnService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblSpecialColumn selectTblSpecialColumnById(Long id);

    public TblSpecialColumn selectTblSpecialColumnByIdView(Long id);
    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblSpecialColumn> selectTblSpecialColumnList(TblSpecialColumn tblSpecialColumn);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 结果
     */
    public int insertTblSpecialColumn(TblSpecialColumn tblSpecialColumn);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 结果
     */
    public int updateTblSpecialColumn(TblSpecialColumn tblSpecialColumn);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTblSpecialColumnByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblSpecialColumnById(Long id);
}
