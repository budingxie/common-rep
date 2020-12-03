package com.py.web.bo;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * description：web请求对象，统一BO结尾
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/2
 */
@Data
public class UserReqBO {

    private Long id;

    private String name;

    @Min(value = 0, message = "年龄不能小于0")
    private Short age;

    private String sex;

    private String email;

    private String ext;
}