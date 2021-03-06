package com.py.web.base.global;

import com.py.web.base.response.Result;
import com.py.web.base.response.ResultUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * description：返回结果统一格式
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@EnableWebMvc
@Configuration
@Order(3)
public class GlobalReturnConfig {

    @RestControllerAdvice
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

        /**
         * 当返回值为true的时候，返回结果的时候，才执行beforeBodyWrite()方法
         *
         * @param methodParameter
         * @param aClass
         * @return
         */
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            Class<?> type = Objects.requireNonNull(methodParameter.getMethod()).getReturnType();
            boolean hasStr = type.isAssignableFrom(String.class);
            boolean hasResult = type.isAssignableFrom(Result.class);
            return !hasStr && !hasResult;
        }

        /**
         * 统一返回格式
         *
         * @param body
         * @param methodParameter
         * @param mediaType
         * @param aClass
         * @param serverHttpRequest
         * @param serverHttpResponse
         * @return
         */
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            return ResultUtils.getSuccessResult(body);
        }
    }

}
