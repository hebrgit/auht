package com.hebo.authDemo.exception;

import com.hebo.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName GloabException
 * @Author hebo
 * @Date 2022/6/19 20:20
 **/
//@ControllerAdvice
@ResponseBody
public class GloabException  {

    /**
     * 系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(UsernameAndPasswordException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Response handleUnexpectedServer(Exception ex) {

        return new Response(HttpStatus.UNAUTHORIZED.value(), "系统发生异常，请联系管理员",null);
    }

}
