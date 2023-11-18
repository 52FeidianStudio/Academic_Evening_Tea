package com.ruoyi.system.service.impl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.TblLike;
import com.ruoyi.system.mapper.TblLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblSpecialColumnMapper;
import com.ruoyi.system.domain.TblSpecialColumn;
import com.ruoyi.system.service.ITblSpecialColumnService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblSpecialColumnServiceImpl implements ITblSpecialColumnService 
{
    @Autowired
    private TblSpecialColumnMapper tblSpecialColumnMapper;
@Autowired
private TblLikeMapper tblLikeMapper;
    /**
     * 增加文章浏览量
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public void addViewNum(Long id)
    {
        TblSpecialColumn tblSpecialColumn = tblSpecialColumnMapper.selectTblSpecialColumnById(id);
        tblSpecialColumn.setViewsNums(tblSpecialColumn.getViewsNums()+1);
        tblSpecialColumnMapper.updateTblSpecialColumn(tblSpecialColumn);
        return ;
    }

    /**
     * 点赞
     * @param id
     */
    @Override
    public void addLike(Long id ,Long userId,String userName) {
        //文章点赞数增加
        TblSpecialColumn tblSpecialColumn = tblSpecialColumnMapper.selectTblSpecialColumnById(id);
        tblSpecialColumn.setLikeCount(tblSpecialColumn.getLikeCount()+1);
        tblSpecialColumnMapper.updateTblSpecialColumn(tblSpecialColumn);
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
        return ;
    }

    /**
     * 浏览文章详细信息
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblSpecialColumn selectTblSpecialColumnById(Long id)
    {

        TblSpecialColumn tblSpecialColumn = tblSpecialColumnMapper.selectTblSpecialColumnById(id);
        TblLike tblLike = new TblLike();
        tblLike.setUserId(SecurityUtils.getUserId());
        tblLike.setSpecialId(id);
        tblLikeMapper.selectTblLike(tblLike);
        tblSpecialColumn.setTblLike(tblLike);
        return tblSpecialColumn;
    }



    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblSpecialColumn> selectTblSpecialColumnList(TblSpecialColumn tblSpecialColumn)
    {
        return tblSpecialColumnMapper.selectTblSpecialColumnList(tblSpecialColumn);
    }

    /**
     * 新增文章
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 结果
     */
    @Override
//    @create
    public int insertTblSpecialColumn(TblSpecialColumn tblSpecialColumn)
    {
//        tblSpecialColumn.setCreateTime(DateUtils.getNowDate());
        String content = tblSpecialColumn.getContent();
        byte[] contentBytes= Base64.getDecoder().decode(content);
        tblSpecialColumn.setContent(new String(contentBytes, StandardCharsets.UTF_8));
        return tblSpecialColumnMapper.insertTblSpecialColumn(tblSpecialColumn);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblSpecialColumn 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblSpecialColumn(TblSpecialColumn tblSpecialColumn)
    {
        if (tblSpecialColumn.getContent()!=null)
        {
            String content = tblSpecialColumn.getContent();
            byte[] contentBytes= Base64.getDecoder().decode(content);
            tblSpecialColumn.setContent(new String(contentBytes, StandardCharsets.UTF_8));
        }
//        tblSpecialColumn.setUpdateTime(DateUtils.getNowDate());
        return tblSpecialColumnMapper.updateTblSpecialColumn(tblSpecialColumn);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblSpecialColumnByIds(Long[] ids)
    {
        return tblSpecialColumnMapper.deleteTblSpecialColumnByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblSpecialColumnById(Long id)
    {
        return tblSpecialColumnMapper.deleteTblSpecialColumnById(id);
    }
}
