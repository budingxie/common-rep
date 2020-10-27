package com.py.cache.service;

import com.py.cache.remote.UserServiceRemote;
import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.inf.UserCacheService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;

/**
 * description：提供数据缓存 的远程接口
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Resource
    public UserServiceRemote userServiceRemote;

    @Override
    @Cacheable(value = "user", key = "#id")
    public UserRespDTO findUserById(Long id) {
        return userServiceRemote.findUserById(id);
    }

}