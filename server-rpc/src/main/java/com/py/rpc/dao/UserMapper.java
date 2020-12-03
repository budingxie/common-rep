package com.py.rpc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.py.rpc.bo.UserBO;
import com.py.rpc.dao.config.DataSource;
import com.py.rpc.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description：查询数据库操作
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 分页查询
     *
     * @return 用户集合
     */
    @DataSource
    List<UserPO> queryUsers();

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return 用户
     */
    UserPO queryUserById(Long id);

    /**
     * 保存用户
     *
     * @param userBO 用户信息
     * @return 是否保存成功
     */
    boolean save(UserBO userBO);

    /**
     * 根据主键更新用户
     *
     * @param userBO 用户信息
     * @return 是否更新成功
     */
    boolean updateUser(UserBO userBO);

    /**
     * 根据主键批量删除
     *
     * @param ids 主键集合
     * @return 删除条数
     */
    int delUser(@Param("ids") List<Long> ids);
}
