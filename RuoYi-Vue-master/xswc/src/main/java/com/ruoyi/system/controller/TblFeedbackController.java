package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.system.domain.TblFeedback;
import com.ruoyi.system.service.ITblFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/feedback")
public class TblFeedbackController extends BaseController
{
    @Autowired
    private ITblFeedbackService tblFeedbackService;

    /**
     * 查询意见反馈
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:list')")
    @PostMapping("/list")
    public TableDataInfo list(TblFeedback tblFeedback)
    {
        startPage();
        List<TblFeedback> list = tblFeedbackService.selectTblFeedbackList(tblFeedback);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblFeedback tblFeedback)
    {
        List<TblFeedback> list = tblFeedbackService.selectTblFeedbackList(tblFeedback);
        ExcelUtil<TblFeedback> util = new ExcelUtil<TblFeedback>(TblFeedback.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblFeedbackService.selectTblFeedbackById(id));
    }

    /**
     * 用户新增意见反馈
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:add')")
    @Log(title = " 用户新增意见反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblFeedback tblFeedback)
    {
        Long userId = SecurityUtils.getUserId();
        tblFeedback.setUserId(userId);
        return toAjax(tblFeedbackService.insertTblFeedback(tblFeedback));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblFeedback tblFeedback)
    {
        return toAjax(tblFeedbackService.updateTblFeedback(tblFeedback));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:feedback:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblFeedbackService.deleteTblFeedbackByIds(ids));
    }
}
