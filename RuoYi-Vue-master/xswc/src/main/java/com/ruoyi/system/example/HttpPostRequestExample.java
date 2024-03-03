package com.ruoyi.system.example;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.App;
import com.ruoyi.system.utils.AliOssUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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
                    + "wx78c9c71118355827"
                    + "&secret="
                    + "3b8e912b8054e4282da847eb6e43165f";
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
                "\"scene\":\"" + activityId.toString() + "\"," +
                "\"check_path\":false," +
                "\"env_version\":\"release\"" +
                "}";
        System.out.println(requestBody);
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



            // 打印响应状态码
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

            String name = UUID.randomUUID().toString() + ".png";

            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 保存图像到本地文件
            if (entity != null) {
       byte[] imageBytes = EntityUtils.toByteArray(entity);

                //存储成图片文件

                // 指定文件路径
                String picturePath = "/var/www/picture/二维码.png";

                try {
                    // 创建文件输出流
                    FileOutputStream fos = new FileOutputStream(picturePath);

                    // 将byte数组写入文件
                    fos.write(imageBytes);

                    // 关闭文件输出流
                    fos.close();

                    System.out.println("Image saved successfully.");
                } catch (IOException e) {
                    System.err.println("Error saving image: " + e.getMessage());
                }


                //合成图片

                try {
                    // 读取底部图片
                    BufferedImage baseImage = ImageIO.read(new File("/var/www/picture/二维码.png"));
                    // 读取要贴上的标签图片
                    BufferedImage labelImage = ImageIO.read(new File("/var/www/picture/报名码标签.png"));


                    //  g.dispose();

                    // 计算标签图片的位置
                    int x = baseImage.getWidth() - labelImage.getWidth();
                    int y = baseImage.getHeight() - labelImage.getHeight();
                    x=x+120;
                    y=y+61;
                    // 缩小标签图片
                    double scale = 0.4; // 缩小为原始大小的一半
                    AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
                    AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage scaledLabelImage = op.filter(labelImage, null);

                    // 在底部图片右下角绘制缩小后的标签图片
                    Graphics2D g = baseImage.createGraphics();
                    g.drawImage(scaledLabelImage, x, y, null);
                    g.dispose();

                    // 将合成后的图片保存到文件
                    File outputFile = new File("/var/www/picture/合成二维码.png");
                    ImageIO.write(baseImage, "jpg", outputFile);

                    System.out.println("图片合成成功！");
                } catch (IOException e) {
                    System.out.println("发生了错误：" + e.getMessage());
                }


//                AliOssUtil aliOssUtil1 = new AliOssUtil();

                //上传

                String repicturePath = "/var/www/picture/合成二维码.png";

                try {
                    // 创建文件输入流
                    FileInputStream fis = new FileInputStream(repicturePath);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    // 从文件中读取数据到缓冲区
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        // 将数据写入到ByteArrayOutputStream中
                        bos.write(buffer, 0, bytesRead);
                    }

                    // 关闭输入流
                    fis.close();

                    // 获取图片的byte数组
                    byte[] reImageBytes = bos.toByteArray();

                    // 关闭ByteArrayOutputStream
                    bos.close();

                    // 现在你可以使用imageBytes了
                    filePath =this.upload(reImageBytes, name);
                    System.out.println("Image converted to byte array successfully.");
                } catch (IOException e) {
                    System.err.println("Error converting image to byte array: " + e.getMessage());
                }


                //删除

                // 创建File对象
                File file = new File(repicturePath);

                // 尝试删除文件
                boolean isDeleted = file.delete();

                if (isDeleted) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }


                //   filePath = this.upload(imageBytes, name);
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
                "\"scene\":\"" + activityId.toString() + "\"," +
                "\"check_path\":false," +
                "\"env_version\":\"release\"" +
                "}";
        System.out.println(requestBody);
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

                //存储成图片文件

                // 指定文件路径
                String picturePath = "/var/www/picture/二维码.png";

                try {
                    // 创建文件输出流
                    FileOutputStream fos = new FileOutputStream(picturePath);

                    // 将byte数组写入文件
                    fos.write(imageBytes);

                    // 关闭文件输出流
                    fos.close();

                    System.out.println("Image saved successfully.");
                } catch (IOException e) {
                    System.err.println("Error saving image: " + e.getMessage());
                }
                //合成图片

                try {
                    // 读取底部图片
                    BufferedImage baseImage = ImageIO.read(new File("/var/www/picture/二维码.png"));
                    // 读取要贴上的标签图片
                    BufferedImage labelImage = ImageIO.read(new File("/var/www/picture/签到码标签.png"));


                    //  g.dispose();

                    // 计算标签图片的位置
                    int x = baseImage.getWidth() - labelImage.getWidth();
                    int y = baseImage.getHeight() - labelImage.getHeight();
                    x=x+120;
                    y=y+61;
                    // 缩小标签图片
                    double scale = 0.4; // 缩小为原始大小的一半
                    AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
                    AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage scaledLabelImage = op.filter(labelImage, null);

                    // 在底部图片右下角绘制缩小后的标签图片
                    Graphics2D g = baseImage.createGraphics();
                    g.drawImage(scaledLabelImage, x, y, null);
                    g.dispose();

                    // 将合成后的图片保存到文件
                    File outputFile = new File("/var/www/picture/合成二维码.png");
                    ImageIO.write(baseImage, "jpg", outputFile);

                    System.out.println("图片合成成功！");
                } catch (IOException e) {
                    System.out.println("发生了错误：" + e.getMessage());
                }


                //上传

                String repicturePath = "/var/www/picture/合成二维码.png";

                try {
                    // 创建文件输入流
                    FileInputStream fis = new FileInputStream(repicturePath);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    // 从文件中读取数据到缓冲区
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        // 将数据写入到ByteArrayOutputStream中
                        bos.write(buffer, 0, bytesRead);
                    }

                    // 关闭输入流
                    fis.close();

                    // 获取图片的byte数组
                    byte[] reImageBytes = bos.toByteArray();

                    // 关闭ByteArrayOutputStream
                    bos.close();

                    // 现在你可以使用imageBytes了
                    filePath =this.upload(reImageBytes, name);
                    System.out.println("Image converted to byte array successfully.");
                } catch (IOException e) {
                    System.err.println("Error converting image to byte array: " + e.getMessage());
                }

                //删除

                // 创建File对象
                File prefile = new File(picturePath);
                File refile = new File(repicturePath);


                // 尝试删除文件
                boolean isDeleted = prefile.delete();
                boolean isDeleted2 = refile.delete();
                if (isDeleted && isDeleted2) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }


                System.out.println("Image saved successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;


    }
}