package com.itheima.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class AliyunOSSOperator {
    //第一种方式
//@Value("${aliyun.oss.endpoint}")
//private String endpoint ;
//
//@Value("${aliyun.oss.bucketName}")
//private String bucketName ;
//
//@Value("${aliyun.oss.region}")
//private String region ;
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content, String originalFilename) throws Exception {

        String endpoint = aliyunOSSProperties.getEndpoint();
        String region = aliyunOSSProperties.getRegion();
        String bucketName = aliyunOSSProperties.getBucketName();

        log.info("params {} {} {}", endpoint, region, bucketName);

        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        String dir = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy/MM"));

        String newFileName = UUID.randomUUID()
                + originalFilename.substring(originalFilename.lastIndexOf("."));

        String objectName = dir + "/" + newFileName;

        ClientBuilderConfiguration configuration =
                new ClientBuilderConfiguration();

        configuration.setSignatureVersion(SignVersion.V4);

        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(configuration)
                .region(region)
                .build();

        try {
            ossClient.putObject(
                    bucketName,
                    objectName,
                    new ByteArrayInputStream(content)
            );
        } finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0]
                + "//"
                + bucketName
                + "."
                + endpoint.split("//")[1]
                + "/"
                + objectName;
    }
}