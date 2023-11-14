package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.annotation.create;
import com.ruoyi.system.annotation.updateActivity;
import com.ruoyi.system.domain.TblActivity;

import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.domain.UserActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 商家发布文章Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Mapper
public interface TblActivityMapper 
{
    /**
     *  获取用户发布活动详细信息
     * 
     * @param id 用户发布活动主键
     * @return 用户发布活动
     */

    public TblActivity selectTblActivityById(Long id);

    /**
     * 用户查询活动集合
     * 
     * @param tblActivity  用户查询活动
     * @return  用户查询活动集合
     */

    public List<TblActivity> selectTblActivityList(TblActivity tblActivity);

    /**
     * 新增商家发布文章
     * 
     * @param tblActivity 商家发布文章
     * @return 结果
     */
    @create
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


    Long getIsEndByActivityId(Long activityId);


}
