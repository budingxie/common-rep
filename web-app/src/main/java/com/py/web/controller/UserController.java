package com.py.web.controller;

import com.py.rpc.dto.PageableDTO;
import com.py.rpc.dto.UserDTO;
import com.py.web.bo.UserReqBO;
import com.py.web.remote.UserServiceRemote;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
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
@Validated
public class UserController {

    @Resource
    private UserServiceRemote userServiceRemote;

    @GetMapping(value = "/users")
    public PageableDTO<List<UserDTO>> users(@Min(value = 1, message = "起始页必须大于0") Integer pageNum,
                                            @Min(value = 1, message = "每页条数必须大于0") Integer pageSize) {
        return userServiceRemote.findUsers(pageNum, pageSize);
    }

    @GetMapping("/id")
    public UserDTO findUserById(Long id) {
        return userServiceRemote.findUserById(id);
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody
                           @Validated UserReqBO userReqBO) {
        return userServiceRemote.addUser(userReqBO);
    }

    @PostMapping("/edit")
    public Boolean editUser(@RequestBody
                            @Validated UserReqBO userReqBO) {
        return userServiceRemote.editUser(userReqBO);
    }

    @PostMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delUser(@RequestBody List<Long> ids) {
        return userServiceRemote.delUser(ids);
    }

}