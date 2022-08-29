package com.learn.boot.utils;

import com.learn.boot.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by dongxie on 2022/8/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Response<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> Response<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> Response<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> Response<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> Response<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> Response<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Response<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> Response<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> Response<T> restResult(T data, int code, String msg) {
        Response<T> apiResult = new Response<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
