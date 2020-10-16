package com.py.dao;

import com.py.dto.UserRespDTO;
import com.py.po.UserPOJO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Component
public class UserMapper {

    private static Map<Long, UserPOJO> dataUser = new HashMap<>();

    static {
        UserPOJO u1 = new UserPOJO(1L, "张三", (short) 18, "男", "123@xx.com", "无");
        UserPOJO u2 = new UserPOJO(2L, "李四", (short) 13, "女", "abc@xx.com", "加急");
        UserPOJO u3 = new UserPOJO(3L, "王五", (short) 29, "男", "qwe@xx.com", "无");
        UserPOJO u4 = new UserPOJO(4L, "赵六", (short) 23, "女", "zxc@xx.com", "无");
        dataUser.put(u1.getId(), u1);
        dataUser.put(u2.getId(), u2);
        dataUser.put(u3.getId(), u3);
        dataUser.put(u4.getId(), u4);
    }

    public List<UserRespDTO> queryUsers() {
        List<UserRespDTO> respUsers = new ArrayList<>();
        for (UserPOJO value : dataUser.values()) {
            UserRespDTO dto = new UserRespDTO(value.getId(), value.getName(), value.getAge(), value.getSex(), value.getEmail());
            respUsers.add(dto);
        }
        return respUsers;
    }

}
