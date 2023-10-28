package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblRecommendCommnetMapper;
import com.ruoyi.system.domain.TblRecommendCommnet;
import com.ruoyi.system.service.ITblRecommendCommnetService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblRecommendCommnetServiceImpl implements ITblRecommendCommnetService 
{
    @Autowired
    private TblRecommendCommnetMapper tblRecommendCommnetMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblRecommendCommnet selectTblRecommendCommnetById(Long id)
    {
        return tblRecommendCommnetMapper.selectTblRecommendCommnetById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblRecommendCommnet> selectTblRecommendCommnetList(TblRecommendCommnet tblRecommendCommnet)
    {
        return tblRecommendCommnetMapper.selectTblRecommendCommnetList(tblRecommendCommnet);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblRecommendCommnet(TblRecommendCommnet tblRecommendCommnet)
    {
        tblRecommendCommnet.setCreateTime(DateUtils.getNowDate());
        return tblRecommendCommnetMapper.insertTblRecommendCommnet(tblRecommendCommnet);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblRecommendCommnet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblRecommendCommnet(TblRecommendCommnet tblRecommendCommnet)
    {
        tblRecommendCommnet.setUpdateTime(DateUtils.getNowDate());
        return tblRecommendCommnetMapper.updateTblRecommendCommnet(tblRecommendCommnet);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblRecommendCommnetByIds(Long[] ids)
    {
        return tblRecommendCommnetMapper.deleteTblRecommendCommnetByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblRecommendCommnetById(Long id)
    {
        return tblRecommendCommnetMapper.deleteTblRecommendCommnetById(id);
    }
}
