package com.example.javaee_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "upload_file")
public class UploadFileDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 文件实际名称
     */
    private String realName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件主名称
     */
    private String primaryName;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 存放路径
     */
    private String path;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 上传人
     */
    private String uploader;

    private Timestamp createTime;


    @Override
    public String toString() {
        return "UploadFile{" +
                "fileName='" + fileName + '\'' +
                ", uploader='" + uploader + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
