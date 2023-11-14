package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TblFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TblGoodsMapper;
import com.ruoyi.system.domain.TblGoods;
import com.ruoyi.system.service.ITblGoodsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Service
public class TblGoodsServiceImpl implements ITblGoodsService 
{
    @Autowired
    private TblGoodsMapper tblGoodsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TblGoods selectTblGoodsById(Long id)
    {
      TblGoods tblGoods=  tblGoodsMapper.selectTblGoodsById(id);
        String picture = tblGoods.getPicture();
        List<String> urlList = Arrays.asList(picture.split("&"));
        tblGoods.setImgs(urlList);
        return tblGoods;
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TblGoods> selectTblGoodsList(TblGoods tblGoods)
    {
        List<TblGoods>  tblGoodsList= tblGoodsMapper.selectTblGoodsList(tblGoods);
        if (tblGoodsList!=null){
            for (TblGoods reTblGood:tblGoodsList) {
                String picture = reTblGood.getPicture();
                List<String> urlList = Arrays.asList(picture.split("&"));
                reTblGood.setImgs(urlList);
            }
        }
        return tblGoodsList;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTblGoods(TblGoods tblGoods)
    {
//        tblGoods.setCreateTime(DateUtils.getNowDate());
        return tblGoodsMapper.insertTblGoods(tblGoods);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tblGoods 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTblGoods(TblGoods tblGoods)
    {
//        tblGoods.setUpdateTime(DateUtils.getNowDate());
        return tblGoodsMapper.updateTblGoods(tblGoods);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblGoodsByIds(Long[] ids)
    {
        return tblGoodsMapper.deleteTblGoodsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTblGoodsById(Long id)
    {
        return tblGoodsMapper.deleteTblGoodsById(id);
    }
}
