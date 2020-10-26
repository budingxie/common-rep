package com.py.web.base.global;

import com.py.web.base.response.Result;
import com.py.web.base.utils.ResultUtils;
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
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            Class<?> type = methodParameter.getMethod().getReturnType();
            boolean hasStr = type.isAssignableFrom(String.class);
            boolean hasResult = type.isAssignableFrom(Result.class);
            return !hasStr && !hasResult;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            return ResultUtils.getSuccessResult(body);
        }
    }

}
