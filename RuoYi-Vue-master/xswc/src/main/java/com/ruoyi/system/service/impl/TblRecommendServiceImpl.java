package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblRecommendMapper;
import com.ruoyi.system.domain.TblRecommend;
import com.ruoyi.system.service.ITblRecommendService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblRecommendServiceImpl implements ITblRecommendService 
{
    @Autowired
    private TblRecommendMapper tblRecommendMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblRecommend selectTblRecommendById(Long id)
    {
        return tblRecommendMapper.selectTblRecommendById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblRecommend> selectTblRecommendList(TblRecommend tblRecommend)
    {
        return tblRecommendMapper.selectTblRecommendList(tblRecommend);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblRecommend(TblRecommend tblRecommend)
    {
//        tblRecommend.setCreateTime(DateUtils.getNowDate());
        return tblRecommendMapper.insertTblRecommend(tblRecommend);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblRecommend(TblRecommend tblRecommend)
    {
//        tblRecommend.setUpdateTime(DateUtils.getNowDate());
        return tblRecommendMapper.updateTblRecommend(tblRecommend);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblRecommendByIds(Long[] ids)
    {
        return tblRecommendMapper.deleteTblRecommendByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblRecommendById(Long id)
    {
        return tblRecommendMapper.deleteTblRecommendById(id);
    }
}
