package com.py.cache.remote;

import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description：远程调用然后提供 缓存数据的接口
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@Component
public class UserServiceRemote {

    @Reference
    private UserService userService;

    public UserRespDTO findUserById(Long id) {
        List<UserRespDTO> users = userService.findUsers();
        for (UserRespDTO user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return new UserRespDTO();
    }
}
