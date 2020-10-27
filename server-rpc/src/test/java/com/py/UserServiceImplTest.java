package com.py;

import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@SpringBootTest(classes = RpcAppBootstrap.class)
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void findUsers() {
        List<UserRespDTO> users = userService.findUsers();
        System.out.println(users);
    }
}
