package com.py.rpc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * description：BO结尾的对象，用来接收请求
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/1
 */
@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = -9186804809962623482L;

    private Long id;

    private String name;

    private Short age;

    private String sex;

    private String email;

    private String ext;
}