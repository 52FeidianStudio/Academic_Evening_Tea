package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.system.domain.TblGoodsUser;
import com.ruoyi.system.service.ITblGoodsUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户兑换积分商品记录Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/usergoods")
public class TblGoodsUserController extends BaseController
{
    @Autowired
    private ITblGoodsUserService tblGoodsUserService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblGoodsUser tblGoodsUser)
    {
        startPage();
        List<TblGoodsUser> list = tblGoodsUserService.selectTblGoodsUserList(tblGoodsUser);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblGoodsUser tblGoodsUser)
    {
        List<TblGoodsUser> list = tblGoodsUserService.selectTblGoodsUserList(tblGoodsUser);
        ExcelUtil<TblGoodsUser> util = new ExcelUtil<TblGoodsUser>(TblGoodsUser.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblGoodsUserService.selectTblGoodsUserById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    @Transactional
    public AjaxResult add(@RequestBody TblGoodsUser tblGoodsUser)
    {
        Long userId = SecurityUtils.getUserId();
        tblGoodsUser.setUserId(userId);
        int res=tblGoodsUserService.insertTblGoodsUser(tblGoodsUser);
        return success(res);
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TblGoodsUser tblGoodsUser)
    {
        return toAjax(tblGoodsUserService.updateTblGoodsUser(tblGoodsUser));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblGoodsUserService.deleteTblGoodsUserByIds(ids));
    }
}
