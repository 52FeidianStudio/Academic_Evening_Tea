package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblRecommendComment;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public interface ITblRecommendCommnetService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblRecommendComment selectTblRecommendCommnetById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblRecommendComment> selectTblRecommendCommnetList(TblRecommendComment tblRecommendCommnet);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 结果
     */
    public int insertTblRecommendCommnet(TblRecommendComment tblRecommendCommnet);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 结果
     */
    public int updateTblRecommendCommnet(TblRecommendComment tblRecommendCommnet);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTblRecommendCommnetByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblRecommendCommnetById(Long id);
}
