package com.py.rpc.service;

import com.py.rpc.dao.UserMapper;
import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserRespDTO> findUsers() {
        return userMapper.queryUsers();
    }
}
