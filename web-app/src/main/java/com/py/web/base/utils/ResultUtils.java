package com.py.web.base.utils;

import com.py.web.base.enums.ResultCode;
import com.py.web.base.response.Result;

/**
 * description：统一返回工具类
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
public class ResultUtils {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result<Void> getSuccessResult() {
        return new Result<Void>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> getSuccessResult(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result<String> getFailResult(String message) {
        return new Result<String>()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
