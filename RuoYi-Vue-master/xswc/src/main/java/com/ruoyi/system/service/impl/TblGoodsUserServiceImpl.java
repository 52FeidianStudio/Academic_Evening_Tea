package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblGoodsUserMapper;
import com.ruoyi.system.domain.TblGoodsUser;
import com.ruoyi.system.service.ITblGoodsUserService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblGoodsUserServiceImpl implements ITblGoodsUserService 
{
    @Autowired
    private TblGoodsUserMapper tblGoodsUserMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblGoodsUser selectTblGoodsUserById(Long id)
    {
        return tblGoodsUserMapper.selectTblGoodsUserById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblGoodsUser> selectTblGoodsUserList(TblGoodsUser tblGoodsUser)
    {
        return tblGoodsUserMapper.selectTblGoodsUserList(tblGoodsUser);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblGoodsUser(TblGoodsUser tblGoodsUser)
    {
//        tblGoodsUser.setCreateTime(DateUtils.getNowDate());
        return tblGoodsUserMapper.insertTblGoodsUser(tblGoodsUser);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblGoodsUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblGoodsUser(TblGoodsUser tblGoodsUser)
    {
//        tblGoodsUser.setUpdateTime(DateUtils.getNowDate());
        return tblGoodsUserMapper.updateTblGoodsUser(tblGoodsUser);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblGoodsUserByIds(Long[] ids)
    {
        return tblGoodsUserMapper.deleteTblGoodsUserByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblGoodsUserById(Long id)
    {
        return tblGoodsUserMapper.deleteTblGoodsUserById(id);
    }
}
