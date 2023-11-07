package com.ruoyi.system.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AliOssProperties {

    @Value("${xswc.alioss.endpoint}")
    private String endpoint;
    @Value("${xswc.alioss.access-key-id}")
    private String accessKeyId;
    @Value("${xswc.alioss.access-key-secret}")
    private  String accessKeySecret;
    @Value("${xswc.alioss.bucket-name}")
    private String bucketName;
}
