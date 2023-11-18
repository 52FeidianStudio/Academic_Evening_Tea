package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 查询文章列表
     */
//    @PreAuthorize("@ss.hasPermi('system:column:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblSpecialColumn tblSpecialColumn)
    {
        startPage();
        List<TblSpecialColumn> list = tblSpecialColumnService.selectTblSpecialColumnList(tblSpecialColumn);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:column:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblSpecialColumn tblSpecialColumn)
    {
        List<TblSpecialColumn> list = tblSpecialColumnService.selectTblSpecialColumnList(tblSpecialColumn);
        ExcelUtil<TblSpecialColumn> util = new ExcelUtil<TblSpecialColumn>(TblSpecialColumn.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 浏览文章详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:column:query')")
    @GetMapping(value = "/{id}/{type}")
    @Transactional
    public AjaxResult getInfo(@PathVariable("id") Long id,@PathVariable("type") Long type)
    {
        if(type==2)
        {
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                   tblSpecialColumnService.addViewNum(id);
                }
            };
            threadPoolTaskExecutor.execute(runnable);
        }

        return success(tblSpecialColumnService.selectTblSpecialColumnById(id));
    }

    /**
     * 新增文章
     */
//    @PreAuthorize("@ss.hasPermi('system:column:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblSpecialColumn tblSpecialColumn)
    {
        return toAjax(tblSpecialColumnService.insertTblSpecialColumn(tblSpecialColumn));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('system:column:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody TblSpecialColumn tblSpecialColumn)
    {
        return toAjax(tblSpecialColumnService.updateTblSpecialColumn(tblSpecialColumn));
    }


    /**
     * 点赞
     * @param
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:column:like')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @GetMapping("/like/{id}")
    @Transactional
    public AjaxResult like(@PathVariable("id") Long id)
    {
        Long userId = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        ReRunnable reRunnable = new ReRunnable(id,userId,username);
        threadPoolTaskExecutor.execute(reRunnable);

        return success();
    }
    /**
     * 点赞
     * @param
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:column:like')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @DeleteMapping("/like/{id}")
    @Transactional
    public AjaxResult dislike(@PathVariable("id") Long id)
    {
        return success(tblSpecialColumnService.disLike(id));
    }


    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:column:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblSpecialColumnService.deleteTblSpecialColumnByIds(ids));
    }


    class ReRunnable implements  Runnable{

        private  String userName;
        private  Long userId;
        private  Long id;
        ReRunnable(Long id,Long userId, String userName){
            this.id=id;
            this.userId=userId;
            this.userName=userName;
        }
        @Override
        public void run() {
            tblSpecialColumnService.addLike(id,userId,userName);
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }


}
