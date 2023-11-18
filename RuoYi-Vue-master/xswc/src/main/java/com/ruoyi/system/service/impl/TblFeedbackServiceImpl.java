package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.annotation.create;
import com.ruoyi.system.constant.CreditConstant;
import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TblCreditUserMapper;
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
    @Autowired
    private SysUserMapper sysUserMapper;
@Autowired
   private TblCreditUserMapper  tblCreditUserMapper;
    /**
     *查询意见反馈
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblFeedback selectTblFeedbackById(Long id)
    {
        TblFeedback tblFeedback=tblFeedbackMapper.selectTblFeedbackById(id);
        String picture = tblFeedback.getPicture();
        List<String> urlList = Arrays.asList(picture.split("&"));
        tblFeedback.setImgs(urlList);
        return tblFeedback;
    }

    /**
     * 查询意见反馈
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblFeedback> selectTblFeedbackList(TblFeedback tblFeedback)
    {
        List<TblFeedback> tblFeedbacks= tblFeedbackMapper.selectTblFeedbackList(tblFeedback);
        if (tblFeedbacks!=null){
            for (TblFeedback reTblFeedback:tblFeedbacks) {
                String picture = reTblFeedback.getPicture();
                List<String> urlList = Arrays.asList(picture.split("&"));
                reTblFeedback.setImgs(urlList);
            }
        }
        return tblFeedbacks;
    }

    /**
     * 用户新增意见反馈
     * 
     * @param tblFeedback 【请填写功能名称】
     * @return 结果
     */
    @Override
//    @create
    public int insertTblFeedback(TblFeedback tblFeedback)
    {
        Long userId = tblFeedback.getUserId();
        //加积分
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        sysUser.setCredit(sysUser.getCredit().add(new BigDecimal(1)));
        sysUserMapper.updateUser(sysUser);
        //加积分记录
        TblCreditUser tblCreditUser = new TblCreditUser();
        tblCreditUser.setUserId(userId);
        tblCreditUser.setCreditNum(new BigDecimal(1));
        tblCreditUser.setCreditType(CreditConstant.FEEDBACK.longValue());
        tblCreditUserMapper.insertTblCreditUser(tblCreditUser);
//        tblFeedback.setCreateTime(DateUtils.getNowDate());
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

//        tblFeedback.setUpdateTime(DateUtils.getNowDate());
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
