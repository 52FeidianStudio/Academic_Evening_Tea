package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.TblUserActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Mapper
public interface TblUserActivityMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblUserActivity selectTblUserActivityById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblUserActivity> selectTblUserActivityList(TblUserActivity tblUserActivity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 结果
     */
@create
    public int insertTblUserActivity(TblUserActivity tblUserActivity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblUserActivity 【请填写功能名称】
     * @return 结果
     */
    public int updateTblUserActivity(TblUserActivity tblUserActivity);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblUserActivityById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTblUserActivityByIds(Long[] ids);

    public int  deleteTblUserActivityByActivityIds(Long[] ids);
}