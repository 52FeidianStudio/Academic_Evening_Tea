package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblFeedbackMapper;
import com.ruoyi.system.domain.TblFeedback;
import com.ruoyi.system.service.ITblFeedbackService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblFeedbackServiceImpl implements ITblFeedbackService 
{
    @Autowired
    private TblFeedbackMapper tblFeedbackMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblFeedback selectTblFeedbackById(Long id)
    {
        return tblFeedbackMapper.selectTblFeedbackById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblFeedback> selectTblFeedbackList(TblFeedback tblFeedback)
    {
        return tblFeedbackMapper.selectTblFeedbackList(tblFeedback);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblFeedback(TblFeedback tblFeedback)
    {
        tblFeedback.setCreateTime(DateUtils.getNowDate());
        return tblFeedbackMapper.insertTblFeedback(tblFeedback);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblFeedback(TblFeedback tblFeedback)
    {
        tblFeedback.setUpdateTime(DateUtils.getNowDate());
        return tblFeedbackMapper.updateTblFeedback(tblFeedback);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblFeedbackByIds(Long[] ids)
    {
        return tblFeedbackMapper.deleteTblFeedbackByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblFeedbackById(Long id)
    {
        return tblFeedbackMapper.deleteTblFeedbackById(id);
    }
}
