package com.py.web.remote;

import com.py.rpc.dto.UserDTO;
import com.py.rpc.inf.UserCacheService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * description：远程调用类，主要是调用远程接口，然后根据逻辑组装结果。
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@Service
public class UserCacheServiceRemote {

    @Reference(check = false, timeout = 3000, interfaceClass = UserCacheService.class)
    private UserCacheService userCacheService;

    public UserDTO findUserById(Long id) {
        return userCacheService.findUserById(id);
    }
}
