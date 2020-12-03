package com.py.web.remote;

import com.py.rpc.bo.UserBO;
import com.py.rpc.dto.PageableDTO;
import com.py.rpc.dto.UserDTO;
import com.py.rpc.inf.UserService;
import com.py.web.bo.UserReqBO;
import com.py.web.traff.AllMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：当需要调用多个远程服务，根据逻辑组装多个结果
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@Service
public class UserServiceRemote {

    AllMapper allMapper = AllMapper.INSTANCE;

    @Reference(check = false, timeout = 3000, interfaceClass = UserService.class)
    private UserService userService;

    public PageableDTO<List<UserDTO>> findUsers(Integer pageNum, Integer pageSize) {
        return userService.findUsers(pageNum, pageSize);
    }

    public boolean addUser(UserReqBO userReqBO) {
        UserBO userBO = allMapper.userReqBO2UserBO(userReqBO);
        return userService.addUser(userBO);
    }

    public boolean editUser(UserReqBO userReqBO) {
        UserBO userBO = allMapper.userReqBO2UserBO(userReqBO);
        return userService.editUser(userBO);
    }

    public boolean delUser(List<Long> ids) {
        return userService.delUser(ids) == ids.size();
    }
}
