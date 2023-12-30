package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.UploadFileDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadFileMapper {
    @Insert("insert into `test`.`upload_file`(realName, fileName, primaryName, extension, path, type, size, uploader) values " +
            "(#{realName},#{fileName},#{primaryName},#{extension},#{path},#{type},#{size},#{uploader})")
    void save(UploadFileDTO U);
}
