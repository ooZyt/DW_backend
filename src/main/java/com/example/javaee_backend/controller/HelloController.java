package com.example.javaee_backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * swagger测试用例
 *
 * @author 2kniden
 * @date 2023/12/15
 */

@RestController
@Api(value = "desc of class")
public class HelloController {

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }


    @ApiOperation(value = "hello one", notes = "")
    @GetMapping(value="/hello1")
    public Object hello( /* 参数注解 */ @ApiParam(value = "desc of param" , required=true ) @RequestParam String name) {
        return "Hello " + name + "!";
    }

}
