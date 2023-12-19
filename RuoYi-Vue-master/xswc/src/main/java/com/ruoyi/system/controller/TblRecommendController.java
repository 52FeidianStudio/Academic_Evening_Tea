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
import com.ruoyi.system.domain.TblRecommend;
import com.ruoyi.system.service.ITblRecommendService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我要推荐板块 Controller
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/system/recommend")
public class TblRecommendController extends BaseController
{
    @Autowired
    private ITblRecommendService tblRecommendService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 查询推荐列表
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:list')")
    @GetMapping("/list")
    public TableDataInfo list(TblRecommend tblRecommend)
    {
        if(tblRecommend.getPageNum()!=null && tblRecommend.getPageSize()!=null)
        {
            startPage();
        }
        List<TblRecommend> list = tblRecommendService.selectTblRecommendList(tblRecommend);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TblRecommend tblRecommend)
    {
        List<TblRecommend> list = tblRecommendService.selectTblRecommendList(tblRecommend);
        ExcelUtil<TblRecommend> util = new ExcelUtil<TblRecommend>(TblRecommend.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * id查询推荐
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tblRecommendService.selectTblRecommendById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TblRecommend tblRecommend)
    {
        return toAjax(tblRecommendService.insertTblRecommend(tblRecommend));
    }


    /**
     * 点赞
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @GetMapping("/like/{id}")
    @Transactional
    public AjaxResult like(@PathVariable("id") Long id)
    {
        Long userId = SecurityUtils.getUserId();
        String userName = SecurityUtils.getUsername();
        tblRecommendService.addLike(id,userId,userName);
        return success();
    }

    /**
     * 取消点赞
     * @param id
     * @return
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @DeleteMapping("/like/{id}")
    @Transactional
    public AjaxResult dislike(@PathVariable("id") Long id)
    {
        return success(tblRecommendService.dislike(id));
    }


    /**
     * 修改推荐状态
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    @Transactional
    public AjaxResult edit(@RequestBody TblRecommend tblRecommend) throws Exception {
        return toAjax(tblRecommendService.updateTblRecommend(tblRecommend));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:recommend:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tblRecommendService.deleteTblRecommendByIds(ids));
    }

}
