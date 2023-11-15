package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.constant.CreditConstant;
import com.ruoyi.system.constant.GoodContsant;
import com.ruoyi.system.constant.ResultConstant;
import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.domain.TblGoods;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TblCreditUserMapper;
import com.ruoyi.system.mapper.TblGoodsMapper;
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
    @Autowired
    private TblGoodsMapper tblGoodsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TblCreditUserMapper tblCreditUserMapper;
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
        //商品数量修改
        Long goodsId = tblGoodsUser.getGoodsId();
        TblGoods tblGoods = tblGoodsMapper.selectTblGoodsById(goodsId);
        Integer nums = tblGoodsUser.getNums();
        Integer allnums = tblGoods.getAllnums();
        Integer resnums=allnums-nums;
        if (resnums<0){//库存不足
            return GoodContsant.INSUFFICIENTGOODS;
        }
        tblGoods.setAllnums(resnums);

        //个人积分修改
        Long userId = tblGoodsUser.getUserId();
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        BigDecimal credit = sysUser.getCredit();
        BigDecimal need=tblGoods.getPrice().multiply(new BigDecimal(nums));
        BigDecimal rescredit = credit.subtract(need);
        if(rescredit.compareTo(BigDecimal.ZERO)<0) {//余额不足
            return GoodContsant.BALANCE;
        }
        sysUser.setCredit(rescredit);

        //增加积分记录
        TblCreditUser tblCreditUser = new TblCreditUser();
        tblCreditUser.setUserId(userId);
        tblCreditUser.setCreditNum(need.negate());
        tblCreditUser.setCreditType(CreditConstant.GOOD.longValue());
        tblCreditUserMapper.insertTblCreditUser(tblCreditUser);
        //修改
        sysUserMapper.updateUser(sysUser);
        tblGoodsMapper.updateTblGoods(tblGoods);
      tblGoodsUserMapper.insertTblGoodsUser(tblGoodsUser);
      int res=3;
        return  res;
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
