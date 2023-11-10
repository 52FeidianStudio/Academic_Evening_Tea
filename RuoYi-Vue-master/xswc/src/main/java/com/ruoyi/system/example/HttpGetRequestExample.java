package com.ruoyi.system.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetRequestExample {
    public static void getSend(){
        try {
            String url = "https://example.com/api/resource";
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 可选：添加请求头
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 输出响应内容
            System.out.println("HTTP Response Code: " + responseCode);
            System.out.println("Response Body:\n" + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}