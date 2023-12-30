package com.example.javaee_backend.exception;


import com.example.javaee_backend.bean.CodeMsg;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 运行时触发异常捕获
 */
@ControllerAdvice
public class ExceptionsHandler {

    private  final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultDTO<Boolean> handle(RuntimeException e) {
        e.printStackTrace();
        if(!CommonUtil.isEmpty(e.getMessage())) {
            logger.info("异常信息={}", e.getMessage());
        }
        return ResultDTO.errorByMsg(CodeMsg.SYSTEM_ERROR);
    }

}
