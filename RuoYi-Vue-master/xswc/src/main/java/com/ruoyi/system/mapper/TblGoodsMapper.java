package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.TblGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Mapper
public interface TblGoodsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TblGoods selectTblGoodsById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TblGoods> selectTblGoodsList(TblGoods tblGoods);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 结果
     */
    @create
    public int insertTblGoods(TblGoods tblGoods);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 结果
     */
    public int updateTblGoods(TblGoods tblGoods);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTblGoodsById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTblGoodsByIds(Long[] ids);
}
