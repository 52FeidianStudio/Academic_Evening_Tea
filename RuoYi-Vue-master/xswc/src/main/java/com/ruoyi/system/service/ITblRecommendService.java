package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblRecommend;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public interface ITblRecommendService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblRecommend selectTblRecommendById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblRecommend> selectTblRecommendList(TblRecommend tblRecommend);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 结果
     */
    public int insertTblRecommend(TblRecommend tblRecommend);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 结果
     */
    public int updateTblRecommend(TblRecommend tblRecommend);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTblRecommendByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblRecommendById(Long id);
}
