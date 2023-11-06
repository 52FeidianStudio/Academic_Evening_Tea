package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.service.TblDeptServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/system/depts")
public class TblDeptController {

    @Autowired
    private  TblDeptServer tblDeptServer;

    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('system:depts:list')")
    public AjaxResult list(SysDept dept)
    {
        List<SysDept> depts = tblDeptServer.selectDeptList(dept);
        return success(depts);
    }


}
