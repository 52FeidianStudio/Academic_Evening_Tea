package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/system/common")
public class xswcCommonController {

    @Autowired
    private  AliOssUtil aliOssUtil;

    @PostMapping("/upload")
//    @PreAuthorize("@ss.hasPermi('system:common:upload')")
public AjaxResult upload(MultipartFile file){
        try {
            //文件原始名
            String originalFilename = file.getOriginalFilename();
            //原始名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构建新名称
            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            return AjaxResult.success(filePath);
        } catch (IOException e) {

        }
        return  AjaxResult.error("文件上传失败");
    }

}
