package nuc.edu.cn.specialtyweb.service.impl;

import io.minio.*;
import io.minio.errors.*;
import nuc.edu.cn.specialtyweb.config.MinioProperties;
import nuc.edu.cn.specialtyweb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties properties;

    @Override
// 上传文件到MinIO存储服务
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // 检查指定的存储桶是否存在
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
        if (!bucketExists){
            // 若存储桶不存在，则创建新的存储桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
            // 为新创建的存储桶设置访问策略
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(properties.getBucketName()).config(createBucketPolicyConfig(properties.getBucketName())).build());
        }

        // 生成唯一的文件名，格式为：日期/UUID-原始文件名
        String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 将文件流上传到MinIO，指定存储桶、对象名称和内容类型
        minioClient.putObject(PutObjectArgs.builder().bucket(properties.getBucketName()).stream(file.getInputStream(), file.getSize(), -1).object(filename).contentType(file.getContentType()).build());

        // 拼接并返回文件的完整访问URL
        return String.join("/",properties.getEndpoint(),properties.getBucketName(),filename);
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
            {
              "Statement" : [ {
                "Action" : "s3:GetObject",
                "Effect" : "Allow",
                "Principal" : "*",
                "Resource" : "arn:aws:s3:::%s/*"
              } ],
              "Version" : "2012-10-17"
            }
            """.formatted(bucketName);
    }
}
