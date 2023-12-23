package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/xswc")
public class FalseController {
    @GetMapping
    public AjaxResult test1(){
        return  AjaxResult.success(false);
    }

    @PostMapping
    public AjaxResult test2(){
        return  AjaxResult.success(false);
    }

}
