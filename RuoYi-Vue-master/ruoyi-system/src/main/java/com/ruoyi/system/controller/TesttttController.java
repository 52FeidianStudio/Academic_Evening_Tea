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
        RRRRRRunnable sssss = new RRRRRRunnable();
        sssss.setParam("sadasdas");
        if(flag==1) {
            executior.execute(sssss);
        }
        return  "test";
    }

   class RRRRRRunnable implements Runnable {

       private String param;

       public void setParam(String param) {
           this.param = param;
       }

       @Override
       public void run() {
           System.out.println("sssss"+this.param);

       }
   }
}
