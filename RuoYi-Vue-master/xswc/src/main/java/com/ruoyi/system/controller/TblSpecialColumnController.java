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
import com.ruoyi.system.domain.TblSpecialColumn;
import com.ruoyi.system.service.ITblSpecialColumnService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 精彩专栏Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/column")
public class TblSpecialColumnController extends BaseController
{
    @Autowired
    private ITblSpecialColumnService tblSpecialColumnService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:column:list')")
    @PostMapping("/list")
    public TableDataInfo list(TblSpecialColumn tblSpecialColumn)
    {
        startPage();
        List<TblSpecialColumn> list = tblSpecialColumnService.selectTblSpecialColumnList(tblSpecialColumn);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:column:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblSpecialColumn tblSpecialColumn)
    {
        List<TblSpecialColumn> list = tblSpecialColumnService.selectTblSpecialColumnList(tblSpecialColumn);
        ExcelUtil<TblSpecialColumn> util = new ExcelUtil<TblSpecialColumn>(TblSpecialColumn.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:column:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblSpecialColumnService.selectTblSpecialColumnById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:column:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblSpecialColumn tblSpecialColumn)
    {
        return toAjax(tblSpecialColumnService.insertTblSpecialColumn(tblSpecialColumn));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:column:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblSpecialColumn tblSpecialColumn)
    {
        return toAjax(tblSpecialColumnService.updateTblSpecialColumn(tblSpecialColumn));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:column:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblSpecialColumnService.deleteTblSpecialColumnByIds(ids));
    }
}
