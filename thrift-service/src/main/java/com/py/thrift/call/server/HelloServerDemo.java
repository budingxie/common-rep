package com.py.thrift.call.server;

import com.py.thrift.service.HelloWorldService;
import com.py.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/2/7
 */
public class HelloServerDemo {

    public static final int SERVER_PORT = 8090;

    public static void main(String[] args) {
        HelloServerDemo server = new HelloServerDemo();
        server.startServer();
    }

    public void startServer() {
        try {
            System.out.println("HelloWorld TSimpleServer start ....");

//          TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new  HelloWorldImpl());
            HelloWorldService.Processor<HelloWorldService.Iface> tprocessor =
                    new HelloWorldService.Processor<>(new HelloWorldServiceImpl());

            // 简单的单线程服务模型，一般用于测试
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
//            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            // tArgs.protocolFactory(new TJSONProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }
}
