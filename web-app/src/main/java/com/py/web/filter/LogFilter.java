package com.py.web.filter;

import com.py.utils.ip.UtilIp;
import com.py.utils.log.UtilTrace;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description：统一拦截，添加日志追踪trace_id，记录访问接口耗时
 *
 * @author pengyou
 * @version 1.0.0
 * @date 2020/11/30
 */
@Slf4j
@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = UtilTrace.get();
        MDC.put(UtilTrace.MDC_TRACE_ID, traceId);
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String uri = request.getRequestURI();
            // 请求进入时间
            long start = System.currentTimeMillis();
            log.info("[Api Access] start. id: {}, uri: {}, method: {}, client: {}, server: {}", traceId,
                    uri, request.getMethod(), getReqIp(request), UtilIp.getRealIp());
            //执行
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("[Api Access]   end. id: {}, uri: {}, duration: {} ms", traceId, uri,
                    System.currentTimeMillis() - start);
        } finally {
            MDC.remove(UtilTrace.MDC_TRACE_ID);
        }
    }

    /**
     * 获取请求机器的ip
     *
     * @param request
     * @return
     */
    private static String getReqIp(HttpServletRequest request) {
        String un = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || un.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || un.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || un.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || un.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || un.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}