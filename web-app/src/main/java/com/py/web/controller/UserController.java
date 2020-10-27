package com.py.web.controller;

import com.py.rpc.dto.UserReqDTO;
import com.py.rpc.dto.UserRespDTO;
import com.py.web.remote.UserCacheServiceRemote;
import com.py.web.remote.UserServiceRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * description：用户接口，对用户信息，查询、添加、修改、删除
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserServiceRemote userServiceRemote;

    @Resource
    private UserCacheServiceRemote userCacheServiceRemote;

    @GetMapping(value = "/users")
    public List<UserRespDTO> users() {
        return userServiceRemote.findUsers();
    }

    @GetMapping("/id")
    public UserRespDTO findUserById(Long id) {
        return userCacheServiceRemote.findUserById(id);
    }

    @GetMapping("/save")
    public void saveUser(@RequestBody UserReqDTO userReqDTO) {

    }

    @GetMapping("/edit")
    public void editUser(List<Long> uIds) {

    }

    @GetMapping("/del")
    public void delUser(List<Long> uIds) {

    }

}
