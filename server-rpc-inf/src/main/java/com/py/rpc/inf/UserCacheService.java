package com.py.rpc.inf;

import com.py.rpc.dto.UserRespDTO;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
public interface UserCacheService {

    UserRespDTO findUserById(Long id);
}
