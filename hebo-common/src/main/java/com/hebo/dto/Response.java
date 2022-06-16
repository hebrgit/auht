package com.hebo.dto;

import java.io.Serializable;

/**
 * @author tech-winning
 * @version 1.0
 * @description: TODO
 * @date 2022/6/16 14:11
 */
public class Response<T> implements Serializable {

    private final static String MSG_FAIL = "失败";

    private final static String MSG_SUCCESS = "成功";

    private final static int FAIL = -1;

    private final static  int SUCCESS = 0;

    private int code;

    private String message;

    private  T data;

    public  Response(){

    }

    public  Response(int code,String message,T data){
        this.code = code;
        this.message= message;
        this.data = data;
    }


    public static <T> Response<T> success(){
        return new Response<T>(SUCCESS,MSG_SUCCESS,null);
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(SUCCESS,MSG_SUCCESS,data);
    }

    public static <T> Response<T> success(int code,String message,T data){
        return new Response<T>(code,message,data);
    }

    public static <T> Response<T> fail(){
        return new Response<T>(FAIL,MSG_FAIL,null);
    }

    public static <T> Response<T> fail(T data){
        return new Response<T>(FAIL,MSG_FAIL,data);
    }

    public static <T> Response<T> fail(int code,String message){
        return new Response<T>(code,message,null);
    }
    public static <T> Response<T> fail(int code,String message,T data){
        return new Response<T>(code,message,data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
