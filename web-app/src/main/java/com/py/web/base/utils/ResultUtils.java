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

    /**
     * 不需要返回值的成功
     *
     * @return Result
     */
    public static Result<Void> getSuccessResult() {
        return new Result<Void>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultCode.SUCCESS.message);
    }

    /**
     * 返回泛型数据
     *
     * @param data 数据
     * @param <T>  任意类型
     * @return Result
     */
    public static <T> Result<T> getSuccessResult(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultCode.SUCCESS.message)
                .setData(data);
    }

    /**
     * 返回500，自定义信息
     *
     * @param message 信息
     * @return Result
     */
    public static <T> Result<T> getFailResult(String message, T data) {
        return new Result<T>()
                .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 从枚举里面获取定义好的错误信息
     *
     * @param resultCode 枚举code
     * @return Result
     */
    public static <T> Result<T> getFailResult(ResultCode resultCode, T data) {
        return new Result<T>()
                .setCode(resultCode)
                .setMessage(resultCode.message)
                .setData(data);
    }
}
