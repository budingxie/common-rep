package com.py.web.base.global;

import com.py.web.base.enums.ResultCode;
import com.py.web.base.response.Result;
import com.py.web.base.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description：全局异常处理
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@RestControllerAdvice
@Slf4j
@Order(2)
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<Exception> handleException(HttpServletRequest request, HttpServletResponse response, final Exception ex) {
        log.error("path:{},errorMsg:{}", request.getServletPath(), ex.getMessage(), ex);
        return ResultUtils.getFailResult(ResultCode.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public Result<Exception> nullPointerEx(HttpServletRequest request, NullPointerException ex) {
        log.error("path:{},errorMsg:{}", request.getServletPath(), ex.getMessage());
        return ResultUtils.getFailResult(ResultCode.NULL_POINTER_ERROR, ex);
    }
}
