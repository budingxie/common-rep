package com.py.thrift.service.impl;

import com.py.thrift.service.HelloWorldService;
import org.apache.thrift.TException;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/2/7
 */
public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    @Override
    public String say(String username) throws TException {
        return "Hi," + username + " ,Welcome to the thrift's world !";
    }
}