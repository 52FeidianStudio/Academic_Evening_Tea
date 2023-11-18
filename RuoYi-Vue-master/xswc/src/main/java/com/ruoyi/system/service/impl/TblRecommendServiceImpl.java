package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.constant.CreditConstant;
import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.domain.TblLike;
import com.ruoyi.system.domain.TblRecommendComment;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private TblRecommendCommentMapper tblRecommendCommnetMapper;

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TblCreditUserMapper tblCreditUserMapper;

    @Autowired
    private TblLikeMapper tblLikeMapper;
    /**
     * id查询推荐
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblRecommend selectTblRecommendById(Long id)
    {
        TblRecommend tblRecommend= tblRecommendMapper.selectTblRecommendById(id);
        //查询评论
        TblRecommendComment tblRecommendCommnet = new TblRecommendComment();
        tblRecommendCommnet.setRecommendId(id);
        List<TblRecommendComment> tblRecommendCommnets = tblRecommendCommnetMapper.selectTblRecommendCommnetList(tblRecommendCommnet);
        tblRecommend.setTblRecommendCommnets(tblRecommendCommnets);
        //查询用户点赞
        TblLike tblLike = new TblLike();
        tblLike.setUserId(SecurityUtils.getUserId());
        tblLike.setRecommendId(id);
        tblLikeMapper.selectTblLike(tblLike);
        tblRecommend.setTblLike(tblLike);
        return tblRecommend;
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
        Long userId = SecurityUtils.getUserId();
        tblRecommend.setUserId(userId);
        return tblRecommendMapper.insertTblRecommend(tblRecommend);
    }

    /**
     * 修改推荐状态
     * 
     * @param tblRecommend 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblRecommend(TblRecommend tblRecommend)
    {
        Long userId = SecurityUtils.getUserId();
        TblRecommend pretblRecommend = tblRecommendMapper.selectTblRecommendById(tblRecommend.getId());
        Integer status = tblRecommend.getStatus();
        if(pretblRecommend.getStatus()==status)
        {
            return tblRecommendMapper.updateTblRecommend(tblRecommend);
        }
        if(status==2)
        {
            //加积分
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            sysUser.setCredit(sysUser.getCredit().add(new BigDecimal(1)));
            sysUserMapper.updateUser(sysUser);
            //加积分记录
            TblCreditUser tblCreditUser = new TblCreditUser();
            tblCreditUser.setUserId(userId);
            tblCreditUser.setCreditNum(new BigDecimal(1));
            tblCreditUser.setCreditType(CreditConstant.RECOMMEND.longValue());
            tblCreditUserMapper.insertTblCreditUser(tblCreditUser);
        }
        if (status==3)
        {
            //加积分
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            sysUser.setCredit(sysUser.getCredit().add(new BigDecimal(2)));
            sysUserMapper.updateUser(sysUser);
            //加积分记录
            TblCreditUser tblCreditUser = new TblCreditUser();
            tblCreditUser.setUserId(userId);
            tblCreditUser.setCreditNum(new BigDecimal(2));
            tblCreditUser.setCreditType(CreditConstant.RECOMMEND.longValue());
            tblCreditUserMapper.insertTblCreditUser(tblCreditUser);
        }
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

    /**
     * 点赞
     * @param id
     */
    @Override
    public void addLike(Long id ,Long userId,String userName) {
        TblRecommend tblRecommend = tblRecommendMapper.selectTblRecommendById(id);
        tblRecommend.setLikeCount(tblRecommend.getLikeCount()+1);
        tblRecommendMapper.updateTblRecommend(tblRecommend);
        //增加点赞记录
        TblLike tblLike = new TblLike();
        tblLike.setSpecialId(id);
        tblLike.setUserId(userId);
        tblLike.setCreateBy(userName);
        LocalDateTime createTime = LocalDateTime.now();
        // 创建一个日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化 LocalDateTime 对象为字符串
        String formattedDateTime = createTime.format(formatter);
        tblLike.setCreateTime(formattedDateTime);
        tblLikeMapper.insertTblLike(tblLike);
    }
}
