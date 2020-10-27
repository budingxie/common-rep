package com.py.web.remote;

import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：当需要调用多个远程服务，把具体逻辑写在这里
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@Service
public class UserServiceRemote {

    @Reference(check = false, timeout = 3000, interfaceClass = UserService.class)
    private UserService userService;

    public List<UserRespDTO> findUsers() {
        return userService.findUsers();
    }
}
