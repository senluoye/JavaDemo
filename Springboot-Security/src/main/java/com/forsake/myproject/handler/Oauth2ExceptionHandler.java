package com.forsake.myproject.handler;

import com.forsake.myproject.entity.ResponseResult;
import com.forsake.myproject.excaption.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Oauth2ExceptionHandler
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 22:47
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<Object> handlerBusinessException(Exception e) {
        System.out.println(System.currentTimeMillis());
        e.printStackTrace();
        return new ResponseResult<>(-1, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Object> handlerServiceException(ServiceException e) {
        System.out.println(System.currentTimeMillis());
        e.printStackTrace();
        return new ResponseResult<>(-1, e.getMessage());
    }
}
