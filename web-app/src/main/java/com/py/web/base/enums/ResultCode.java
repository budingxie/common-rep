package com.py.web.base.enums;

/**
 * description：常用的服务端状态码
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
public enum ResultCode {

    // 成功
    SUCCESS(200, "success"),

    // 失败
    FAIL(400, "请求错误"),

    // 请求参数错误
    PARAMETER_ERROR(400, "请求参数有误"),

    // 未认证（签名错误）,
    UNAUTHORIZED(401, "权限错误"),

    // 接口不存在
    NOT_FOUND(404, "接口不存在"),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    // 如使用put方式，访问接口，而接口支持post
    NOT_IMPLEMENTED(501, "请求方式未实现"),

    // 空指针错误
    NULL_POINTER_ERROR(502, "空指针错误");


    public int code;

    public String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
