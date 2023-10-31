package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TblActivity;

/**
 * 商家发布文章Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */

public interface TblActivityMapper 
{
    /**
     * 查询商家发布文章
     * 
     * @param id 商家发布文章主键
     * @return 商家发布文章
     */
    public TblActivity selectTblActivityById(Long id);

    /**
     * 查询商家发布文章列表
     * 
     * @param tblActivity 商家发布文章
     * @return 商家发布文章集合
     */
    public List<TblActivity> selectTblActivityList(TblActivity tblActivity);

    /**
     * 新增商家发布文章
     * 
     * @param tblActivity 商家发布文章
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
     * 删除商家发布文章
     * 
     * @param id 商家发布文章主键
     * @return 结果
     */
    public int deleteTblActivityById(Long id);

    /**
     * 批量删除商家发布文章
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTblActivityByIds(Long[] ids);
}
