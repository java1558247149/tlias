package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    //上传本地
    /*@PostMapping("/upload")
    public Result post(String name, String age, MultipartFile file) throws IOException {
        log.info("接收参数,{},{},{}", name, age, file);
        //获取后缀名
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        //获取随机字符
        String replace = UUID.randomUUID().toString().replace("-", "");
        String resultName = replace + substring;

        file.transferTo(new File("E:/image/" + resultName));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传的文件为，{}", file);
        String upload = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info(upload);
        return Result.success(upload);
    }
}
