package com.py.rpc.inf;

import com.py.rpc.dto.UserDTO;

/**
 * description：dubbo接口
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
public interface UserCacheService {

    /**
     * 根据id查询用户，会先查询缓存
     *
     * @param id 用户id
     * @return 用户
     */
    UserDTO findUserById(Long id);
}
