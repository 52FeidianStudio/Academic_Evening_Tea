package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.DeptActivity;
import com.ruoyi.system.service.IDetpAcitivtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/deptactivity")
public class DeptActivityController {

    @Autowired
    private IDetpAcitivtyService detpAcitivtyService;
    @PutMapping()
    public AjaxResult edit(@RequestBody DeptActivity deptActivity){
        detpAcitivtyService.updateDeptActivity(deptActivity);
        return AjaxResult.success("修改成功");
    }
}
