package com.py.rpc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.py.rpc.dao.config.DataSource;
import com.py.rpc.dto.UserRespDTO;
import com.py.rpc.po.UserPOJO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Repository
public interface UserMapper extends BaseMapper<UserPOJO> {

    @DataSource("doris")
    List<UserRespDTO> queryUsers();
}
