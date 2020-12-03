package com.py.rpc.inf;

import com.py.rpc.bo.UserBO;
import com.py.rpc.dto.PageableDTO;
import com.py.rpc.dto.UserDTO;

import java.util.List;

/**
 * description：dubbo接口
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param pageNum  起始页
     * @param pageSize 每页大小
     * @return 分页数据
     */
    PageableDTO<List<UserDTO>> findUsers(Integer pageNum, Integer pageSize);

    /**
     * 根据主键查询用户
     *
     * @param id 主键
     * @return 用户
     */
    UserDTO findUserById(Long id);

    /**
     * 保存用户
     *
     * @param userBO 用户信息
     * @return 是否成功
     */
    boolean addUser(UserBO userBO);

    /**
     * 更新用户
     *
     * @param userBO 用户信息
     * @return 是否成功
     */
    boolean editUser(UserBO userBO);

    /**
     * 批量删除用户
     *
     * @param ids 用户主键集合
     * @return 是否成功
     */
    int delUser(List<Long> ids);
}
