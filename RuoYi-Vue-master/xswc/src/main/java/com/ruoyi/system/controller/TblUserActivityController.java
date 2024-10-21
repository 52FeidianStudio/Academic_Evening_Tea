package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.domain.UserActivity;
import com.ruoyi.system.mapper.TblActivityMapper;
import com.ruoyi.system.service.ITblActivityService;
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
import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.service.ITblUserActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户报名活动 Controller
 *
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/useractivity")
public class TblUserActivityController extends BaseController
{
    @Autowired
    private ITblUserActivityService tblUserActivityService;
    @Autowired
    private ITblActivityService tblActivityService;


    /**
     * 用户报名活动
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:application')")
    @PostMapping()
    public AjaxResult application(@RequestBody TblUserActivity tblUserActivity)
    {
        tblUserActivity.setUserId(SecurityUtils.getUserId());
      int res=  tblUserActivityService.insertTblUserActivity(tblUserActivity);
       if(res==0){
           return AjaxResult.error("报名失败，人数达到上限");
       }
       else if(res==-1){
           return AjaxResult.error("报名时间已过");
       }
       else if(res==-2){
           return  AjaxResult.error("已经报过名");
       }else if(res==-3){
           return AjaxResult.error("报名人数过多，请稍后重试");
       }
       return success();
    }


    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:useractivity:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblUserActivity tblUserActivity)
    {
        startPage();
        List<TblUserActivity> list = tblUserActivityService.selectTblUserActivityList(tblUserActivity);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:useractivity:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblUserActivity tblUserActivity)
    {
        List<TblUserActivity> list = tblUserActivityService.selectTblUserActivityList(tblUserActivity);
        ExcelUtil<TblUserActivity> util = new ExcelUtil<TblUserActivity>(TblUserActivity.class);
        util.exportExcel(response, list, "活动报名人数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:useractivity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblUserActivityService.selectTblUserActivityById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:add')")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody TblUserActivity tblUserActivity)
//    {
//        return toAjax(tblUserActivityService.insertTblUserActivity(tblUserActivity));
//    }

    /**
     *用户签到
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:register')")
    @Log(title = "用户签到", businessType = BusinessType.UPDATE)
    @PutMapping("/register")
    public AjaxResult edit(@RequestBody TblUserActivity tblUserActivity)
    {
        return success(tblUserActivityService.updateTblUserActivity(tblUserActivity));
    }

    /**
     * 个人中心  我的活动
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:myactivity')")
    @Log(title = "我的活动", businessType = BusinessType.UPDATE)
    @GetMapping("/myactivity")
public  AjaxResult myactivity(){
        TblUserActivity tblUserActivity = new TblUserActivity();
        tblUserActivity.setUserId(SecurityUtils.getUserId());
        return success(tblUserActivityService.selectMyActivity(tblUserActivity));
    }
    /**
     * 个人中心  我发布的活动
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:myactivity')")
    @Log(title = "我发布的活动", businessType = BusinessType.UPDATE)
    @GetMapping("/myaddactivity")
    public  AjaxResult myaddactivity(){
          TblActivity tblActivity= new TblActivity();
          tblActivity.setUserId(SecurityUtils.getUserId());
        return success(tblActivityService.selectTblActivityList(tblActivity));
    }



    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:useractivity:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblUserActivityService.deleteTblUserActivityByIds(ids));
    }

}
