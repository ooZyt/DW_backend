package com.example.javaee_backend.service.impl;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.example.javaee_backend.mapper.UploadFileMapper;
import com.example.javaee_backend.pojo.UploadFileDTO;
import com.example.javaee_backend.service.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UploadFileToolImpl implements UploadFileTool {
    private final UploadFileMapper uploadFileMapper;

    @Value("${spring.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${spring.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${spring.oss.bucketName}")
    private String bucketName;
    @Value("${spring.oss.endpoint}")
    private String endPoint;

    private long maxSize = 10;

    @Autowired
    public UploadFileToolImpl(UploadFileMapper uploadFileMapper) {
        this.uploadFileMapper = uploadFileMapper;
    }


    /**
     * 上传文件到服务器
     * @param uploader  上传人
     * @param realName  文件实际名称
     * @param multipartFile 文件
     * @return
     */
    @Override
    public UploadFileDTO upload(String uploader, String realName, MultipartFile multipartFile)  {
        //阿里云oss相关设置
        //创建OSSClient实例。
        OSS ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;

        //检查文件大小
        if (multipartFile.getSize() > maxSize * 1024 * 1024) {
            throw new RuntimeException("超出文件上传大小限制" + maxSize + "MB");
        }
        InputStream inputStream;
        try {
            //获取上传文件输入流
            inputStream = multipartFile.getInputStream();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //获取上传文件的主文件名与扩展名
        String primaryName = FileUtil.mainName(multipartFile.getOriginalFilename());
        String extension = FileUtil.extName(multipartFile.getOriginalFilename());
        //根据文件扩展名得到文件类型
        String type = getFileType(extension);
        //给上传的文件加上时间戳
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmmssS");
        String nowStr = "-" + date.format(format);
        String fileName = primaryName + nowStr + "." + extension;

        try {
            //上传文件到阿里云oss
            File localFile = null;
            localFile = File.createTempFile("temp", null);
            multipartFile.transferTo(localFile);
            String key = "upload/"+fileName;
            ossClient.putObject(bucketName,key,inputStream);

            //存储到数据库
            UploadFileDTO uploadFile = new UploadFileDTO(null, realName, fileName, primaryName,
                    extension, "https://"+endPoint+key, type, multipartFile.getSize(),
                    uploader, Timestamp.valueOf(LocalDateTime.now()));
            uploadFileMapper.save(uploadFile);
            return uploadFile;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{
            //关闭OSSClient
            ossClient.shutdown();
        }

    }

    /**
     * 根据文件扩展名给文件类型
     *
     * @param extension 文件扩展名
     * @return 文件类型
     */
    private static String getFileType(String extension) {
        String document = "txt doc pdf ppt pps xlsx xls docx csv";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if (image.contains(extension)) {
            return "image";
        } else if (document.contains(extension)) {
            return "document";
        } else if (music.contains(extension)) {
            return "music";
        } else if (video.contains(extension)) {
            return "video";
        } else {
            return "other";
        }
    }
}
