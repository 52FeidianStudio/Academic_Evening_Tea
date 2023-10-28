package com.ruoyi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testtest")
public class TesttttController {
    @Autowired
    private  ThreadPoolTaskExecutor executior;

    @GetMapping("/test")
    public String testController(String id,Integer flag) {
        if(flag==1) {
            executior.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("sssss");
                }
            });
        }
        return  "test";
    }
}
