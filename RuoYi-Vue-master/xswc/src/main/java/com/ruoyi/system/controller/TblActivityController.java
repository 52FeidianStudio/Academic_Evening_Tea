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
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.service.ITblActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家发布文章Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/activity")
public class TblActivityController extends BaseController
{
    @Autowired
    private ITblActivityService tblActivityService;

    /**
     * 查询商家发布文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblActivity tblActivity)
    {
        startPage();
        List<TblActivity> list = tblActivityService.selectTblActivityList(tblActivity);
        return getDataTable(list);
    }

    /**
     * 导出商家发布文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "商家发布文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblActivity tblActivity)
    {
        List<TblActivity> list = tblActivityService.selectTblActivityList(tblActivity);
        ExcelUtil<TblActivity> util = new ExcelUtil<TblActivity>(TblActivity.class);
        util.exportExcel(response, list, "商家发布文章数据");
    }

    /**
     * 获取商家发布文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblActivityService.selectTblActivityById(id));
    }

    /**
     * 新增商家发布文章
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "商家发布文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblActivity tblActivity)
    {
        return toAjax(tblActivityService.insertTblActivity(tblActivity));
    }

    /**
     * 修改商家发布文章
     */
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "商家发布文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblActivity tblActivity)
    {
        return toAjax(tblActivityService.updateTblActivity(tblActivity));
    }

    /**
     * 删除商家发布文章
     */
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "商家发布文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblActivityService.deleteTblActivityByIds(ids));
    }
}
