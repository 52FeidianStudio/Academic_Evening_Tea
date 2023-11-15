package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.TblFeedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Mapper
public interface TblFeedbackMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblFeedback selectTblFeedbackById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblFeedback> selectTblFeedbackList(TblFeedback tblFeedback);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 结果
     */
    @create
    public int insertTblFeedback(TblFeedback tblFeedback);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 结果
     */
    public int updateTblFeedback(TblFeedback tblFeedback);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblFeedbackById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTblFeedbackByIds(Long[] ids);
}
