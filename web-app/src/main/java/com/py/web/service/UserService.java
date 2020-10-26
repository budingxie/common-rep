package com.py.web.service;

import com.py.web.dto.UserRespDTO;

import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
public interface UserService {

    List<UserRespDTO> findUsers();
}
