package com.example.javaee_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan({"com.example.javaee_backend.dao", "com.example.javaee_backend.mapper"})
public class JavaeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeeBackendApplication.class, args);
    }

}
