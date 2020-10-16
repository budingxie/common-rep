package com.py.base.global;

import com.py.base.response.Result;
import com.py.base.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * description：统一全局参数校验
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Slf4j
@RestControllerAdvice
@Order(1)
public class GlobalRequestParamValidated {

    /**
     * 表单参数校验
     *
     * @param request
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public Result<String> bindEx(HttpServletRequest request, BindException ex) {
        log.error("path:{},errorMsg:{}", request.getServletPath(), ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return ResultUtils.getFailResult(message);
    }

    /**
     * json参数校验
     *
     * @param request
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> bindEx(HttpServletRequest request, MethodArgumentNotValidException ex) {
        log.error("path:{},errorMsg:{}", request.getServletPath(), ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return ResultUtils.getFailResult(message);
    }
}
