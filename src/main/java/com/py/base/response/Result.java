package com.py.base.response;


import com.py.base.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * description：统一api响应结果封装
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6057150722599024203L;

    private int code;

    private String message;

    private T data;

    public Result<T> setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
