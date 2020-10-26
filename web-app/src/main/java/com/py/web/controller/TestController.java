package com.py.web.controller;

import com.py.web.base.response.Result;
import com.py.web.base.utils.ResultUtils;
import com.py.web.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/15
 */
@RestController
public class TestController {

    @GetMapping("/")
    public User context() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        return user;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/success")
    public Result<Void> success() {
        return ResultUtils.getSuccessResult();
    }

    @GetMapping("/testEx")
    public String testException(@RequestParam(value = "id") Long id) {
        if (id == 1) {
            throw new NullPointerException("查询结果为空");
        }
        return "ok";
    }

    @PostMapping("/formRequest")
    public User formRequest(@Validated User form) {
        User user = new User();
        user.setId(15L);
        user.setAge(form.getAge());
        user.setName(form.getName());
        return user;
    }

    @PostMapping("/jsonRequest")
    public User jsonRequest(@RequestBody @Validated User form) {
        User user = new User();
        user.setId(15L);
        user.setAge(form.getAge());
        user.setName(form.getName());
        return user;
    }
}
