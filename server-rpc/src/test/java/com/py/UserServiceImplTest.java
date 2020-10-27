package com.py;

import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * description：测试服务
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@SpringBootTest(classes = RpcAppBootstrap.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void findUsers() {
        List<UserRespDTO> users = userService.findUsers();
        System.out.println(users);
    }
}
