package com.py.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Data
public class UserReqDTO implements Serializable {

    private static final long serialVersionUID = -6564802753137306647L;

    public String name;

    public Short age;

    public String sex;

    public String email;

    @Override
    public String toString() {
        return "UserReqDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
