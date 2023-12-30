package com.example.DW_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class DWBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DWBackendApplication.class, args);
    }

}
