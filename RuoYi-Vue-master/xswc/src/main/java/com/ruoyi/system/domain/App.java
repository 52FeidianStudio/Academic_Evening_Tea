package com.ruoyi.system.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class App {
    @Value("${app.appid}")
    private  String appid;
    @Value("${app.appsecret}")
    private  String appsecret;
    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }
}
