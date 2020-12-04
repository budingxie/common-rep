package com.py.rpc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.py.rpc.bo.UserBO;
import com.py.rpc.dao.UserMapper;
import com.py.rpc.dto.PageableDTO;
import com.py.rpc.dto.UserDTO;
import com.py.rpc.inf.UserService;
import com.py.rpc.po.UserPO;
import com.py.rpc.traff.AllMapper;
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

    /**
     * copy对象工具
     */
    AllMapper allMapper = AllMapper.INSTANCE;

    @Resource
    private UserMapper userMapper;

    @Override
    public PageableDTO<List<UserDTO>> findUsers(Integer pageNum, Integer pageSize) {
        int start = (pageNum - 1) * pageSize;
        // 分页插件
        PageHelper.startPage(start, pageSize);
        List<UserPO> userPoList = userMapper.queryUsers();
        PageInfo<UserPO> pageInfo = new PageInfo<>(userPoList);
        return allMapper.pageInfo2Pageable(pageInfo);
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserPO userPO = userMapper.queryUserById(id);
        return allMapper.userPO2DTO(userPO);
    }

    @Override
    public boolean addUser(UserBO userBO) {
        return userMapper.save(userBO);
    }

    @Override
    public boolean editUser(UserBO userBO) {
        return userMapper.updateUser(userBO);
    }

    @Override
    public int delUser(List<Long> ids) {
        return userMapper.delUser(ids);
    }
}