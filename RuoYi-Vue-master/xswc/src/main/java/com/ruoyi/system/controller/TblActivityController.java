package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ITblUserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.service.ITblActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动详情Controller
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

    @Autowired
    private ITblUserActivityService tblUserActivityService;

    /**
     * 用户查询活动集合
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @PostMapping("/list")
    public TableDataInfo Userlist(@RequestBody TblActivity tblActivity)
    {
//        startPage();
        List<TblActivity> list = tblActivityService.selectTblActivityList(tblActivity);
        return getDataTable(list);
    }

    //查看是否具有发布活动的权限
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @GetMapping("/menu")
    public  AjaxResult menu(){
        return success("可以访问");
    }


    /**
     * 导出商家发布文章列表
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "商家发布文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblActivity tblActivity)
    {

        List<TblActivity> list = tblActivityService.selectTblActivityList(tblActivity);
        ExcelUtil<TblActivity> util = new ExcelUtil<TblActivity>(TblActivity.class);
        util.exportExcel(response, list, "商家发布文章数据");
    }

    /**
     * 用户获取发布活动详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblActivityService.selectTblActivityById(id));
    }




    /**
     * 新增用户发布活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "用户发布活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblActivity tblActivity)
    {
        tblActivity.setUserId(SecurityUtils.getUserId());
        return toAjax(tblActivityService.insertTblActivity(tblActivity));
    }

    /**
     * 修改商家发布活动
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "修改商家发布活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblActivity tblActivity)
    {
        return toAjax(tblActivityService.updateTblActivity(tblActivity));
    }

    /**
     * 删除商家发布活动
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "删除商家发布活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Transactional
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        tblActivityService.deleteTblActivityByIds(ids);
        tblUserActivityService.deleteTblUserActivityByActivityIds(ids);
        return success();
    }
}
