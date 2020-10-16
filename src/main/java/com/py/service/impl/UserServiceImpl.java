package com.py.service.impl;

import com.py.dao.UserMapper;
import com.py.dto.UserRespDTO;
import com.py.service.UserService;
import org.springframework.stereotype.Service;

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
