package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.UploadFileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UploadFileTool {

    /**
     * 文件上传
     *
     * @param uploader  上传人
     * @param realName  文件实际名称
     * @param multipartFile 文件
     * @return 上传信息
     */
    UploadFileDTO upload(String uploader, String realName, MultipartFile multipartFile) ;
}
