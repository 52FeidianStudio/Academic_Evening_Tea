package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/xswc")
public class FalseController {
    @GetMapping
    public AjaxResult test(){
        return  AjaxResult.success(true);
    }

}
