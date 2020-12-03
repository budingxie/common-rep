package com.py.rpc.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description：PO结尾的对象，是数据库中对应的表
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO implements Serializable {

    private static final long serialVersionUID = -5315617693305084123L;

    private Long id;

    private String name;

    private Short age;

    private String sex;

    private String email;

    private String ext;

    @Override
    public String toString() {
        return "UserPOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}