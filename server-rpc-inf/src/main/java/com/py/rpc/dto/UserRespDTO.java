package com.py.rpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO implements Serializable {

    private static final long serialVersionUID = 2375788693489587143L;

    private Long id;

    private String name;

    private Short age;

    private String sex;

    private String email;

    @Override
    public String toString() {
        return "UserRespDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
