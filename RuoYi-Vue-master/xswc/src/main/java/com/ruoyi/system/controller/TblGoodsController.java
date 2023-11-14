package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TblGoods;
import com.ruoyi.system.service.ITblGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 积分商品Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/goods")
public class TblGoodsController extends BaseController
{
    @Autowired
    private ITblGoodsService tblGoodsService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblGoods tblGoods)
    {
        startPage();
        List<TblGoods> list = tblGoodsService.selectTblGoodsList(tblGoods);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblGoods tblGoods)
    {
        List<TblGoods> list = tblGoodsService.selectTblGoodsList(tblGoods);
        ExcelUtil<TblGoods> util = new ExcelUtil<TblGoods>(TblGoods.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblGoodsService.selectTblGoodsById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblGoods tblGoods)
    {
        return toAjax(tblGoodsService.insertTblGoods(tblGoods));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblGoods tblGoods)
    {
        return toAjax(tblGoodsService.updateTblGoods(tblGoods));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblGoodsService.deleteTblGoodsByIds(ids));
    }
}
