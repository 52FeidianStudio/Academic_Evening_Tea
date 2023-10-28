package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblGoodsUser;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public interface ITblGoodsUserService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblGoodsUser selectTblGoodsUserById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblGoodsUser> selectTblGoodsUserList(TblGoodsUser tblGoodsUser);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 结果
     */
    public int insertTblGoodsUser(TblGoodsUser tblGoodsUser);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 结果
     */
    public int updateTblGoodsUser(TblGoodsUser tblGoodsUser);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTblGoodsUserByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblGoodsUserById(Long id);
}
