package com.py.rpc.service;

import com.py.rpc.dao.UserMapper;
import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * description：rpc实现。测试的时候加入@Component，正常服务提供不需要
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserRespDTO> findUsers() {
        return userMapper.queryUsers();
    }
}
