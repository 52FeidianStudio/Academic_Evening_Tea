package com.ruoyi.system.example;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.App;
import com.ruoyi.system.utils.AliOssUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Controller
public class HttpPostRequestExample {

    @Autowired
    private App app;

    @Autowired
    private AliOssUtil aliOssUtil;

    public String upload(byte[] bytes, String objectName) {
        String  endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId ="LTAI5tGYRrYBPsmRP7zcytD7";
        String accessKeySecret="W9zWoY3RGdbeme6z9u2LNnuLQewpPX";
        String bucketName="xswc";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (Exception ce) {
            System.out.println("Caught an Exception, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        return stringBuilder.toString();
    }



    public String postSendAccessToken() {
        StringBuffer sb = new StringBuffer();
        List<String> list2 = null;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + "wx3bcba825f1b221e8"
                    + "&secret="
                    + "daaff5fa37b779b83285460af74571b9";
            URL obj = new URL(url);
            System.out.println(obj);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // 设置请求方法为POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 可选：添加请求头
//            connection.setRequestProperty("Content-Type", "application/json");
            // 构建请求体
//            String requestBody = "{\"key\":\"value\"}";

            // 发送请求体数据
            OutputStream os = connection.getOutputStream();
//            os.write(requestBody.getBytes());
            os.flush();
            os.close();

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            // 处理响应...
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(connection
                    .getInputStream(), "utf-8"));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine);
                List<String> list1 = Arrays.asList(sb.toString().split(":"));
                System.out.println(list1.get(1));
                list2 = Arrays.asList(list1.get(1).split(","));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2.get(0).trim().replace("\"", "");
    }


    public String postApplication(String accessToken, Long activityId) {
        String filePath = null;
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="
                + accessToken;
        // 请求体
        String requestBody = "{" +
                "\"page\":\"pages/activity/ActivityDetails\"," +
                "\"scene\":\"id=" + activityId.toString() + "\"," +
                "\"check_path\":false," +
                "\"env_version\":\"release\"" +
                "}";

        try {
            // 创建 HttpClient 对象
            HttpClient httpClient = HttpClients.createDefault();

            // 创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            httpPost.setEntity(new StringEntity(requestBody));

            // 执行请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 打印响应状态码
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

            String name = UUID.randomUUID().toString() + ".png";

            // 保存图像到本地文件
            if (entity != null) {
                byte[] imageBytes = EntityUtils.toByteArray(entity);
//                AliOssUtil aliOssUtil1 = new AliOssUtil();
                filePath = this.upload(imageBytes, name);
                System.out.println("Image saved successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }


    public String postSignIn(String accessToken, Long activityId) {
        String filePath = null;
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="
                + accessToken;
        // 请求体
        String requestBody = "{" +
                "\"page\":\"pages/activity/SignCheck\"," +
                "\"scene\":\"id=" + activityId.toString() + "\"," +
                "\"check_path\":false," +
                "\"env_version\":\"release\"" +
                "}";

        try {
            // 创建 HttpClient 对象
            HttpClient httpClient = HttpClients.createDefault();

            // 创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            httpPost.setEntity(new StringEntity(requestBody));

            // 执行请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 打印响应状态码
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

            String name = UUID.randomUUID().toString() + ".png";

            // 保存图像到本地文件
            if (entity != null) {
                byte[] imageBytes = EntityUtils.toByteArray(entity);
                filePath =this.upload(imageBytes, name);
                System.out.println("Image saved successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;


    }
}