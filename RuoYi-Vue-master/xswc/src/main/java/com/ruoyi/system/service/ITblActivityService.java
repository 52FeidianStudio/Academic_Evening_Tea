package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.domain.vo.TblActivityVO;

/**
 * 商家发布文章Service接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public interface ITblActivityService 
{
    /**
     *  获取用户发布活动详细信息
     * 
     * @param id 商家发布文章主键
     * @return 商家发布文章
     */
    public TblActivity selectTblActivityById(Long id);

    /**
     * 用户查询活动集合
     * 
     * @param tblActivity 商家发布文章
     * @return 商家发布文章集合
     */
    public List<TblActivity> selectTblActivityList(TblActivity tblActivity);

    /**
     * 新增用户发布活动
     * 
     * @param tblActivity 用户发布活动
     * @return 结果
     */
    public int insertTblActivity(TblActivity tblActivity);

    /**
     * 修改商家发布文章
     * 
     * @param tblActivity 商家发布文章
     * @return 结果
     */
    public int updateTblActivity(TblActivity tblActivity);

    /**
     * 批量删除商家发布文章
     * 
     * @param ids 需要删除的商家发布文章主键集合
     * @return 结果
     */
    public int deleteTblActivityByIds(Long[] ids);

    /**
     * 删除商家发布文章信息
     * 
     * @param id 商家发布文章主键
     * @return 结果
     */
    public int deleteTblActivityById(Long id);
}
