package com.py.rpc.filter;


import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/30
 */

@Activate(group = Constants.PROVIDER, order = -999)
public class GlobalServiceFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(GlobalServiceFilter.class);

    private Logger msgLogger = LoggerFactory.getLogger("Request-Message");

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;

        msgLogger.info("IP:{}, Service:{}, Method:{}, Request:{}.",
                RpcContext.getContext().getRemoteHost(),
                invoker.getInterface().getName(),
                invocation.getMethodName(),
                Arrays.toString(invocation.getArguments()));

        long startTime = System.currentTimeMillis();

        try {
            result = invoker.invoke(invocation);

            if (result.getException() instanceof Exception) {
                throw new Exception(result.getException());
            }
        } catch (Exception e) {
            log.info("IP:{}, Service:{}, Method:{}, Exception:{}.",
                    RpcContext.getContext().getRemoteHost(),
                    invoker.getInterface().getName(),
                    invocation.getMethodName(),
                    e.toString());
        } finally {
            msgLogger.info("IP:{}, Service:{}, Method:{}, Response:{}.",
                    RpcContext.getContext().getRemoteHost(),
                    invoker.getInterface().getName(),
                    invocation.getMethodName(),
                    result);

            log.info("IP:{}, Service:{}, Method:{}, consume: {} ms.",
                    RpcContext.getContext().getRemoteHost(),
                    invoker.getInterface().getName(),
                    invocation.getMethodName(),
                    System.currentTimeMillis() - startTime);
        }

        return result;
    }

}
