package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.User;
import com.bin.aircondition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-11-01
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("login")
    public Result login(@RequestBody User user) {

        String token = userService.login(user);
        return Result.ok().data("token", token);
    }

    //注册用户
    @PostMapping("register")
    public Result register(@RequestBody User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User user1 = userService.getOne(wrapper);
        if(!StringUtils.isEmpty(user1)) {
            return Result.error().message("该用户名已被注册~");
        }
        boolean save = userService.save(user);
        if(!save) {
            return Result.error().message("注册失败了!");
        }
        return Result.ok().message("注册成功, 可以登陆了");
    }
    //修改密码
    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        userService.update(user, wrapper);
        return Result.ok().data("token", null).message("密码修改成功!请重新登陆");
    }

    //根据token获取用户信息
    @GetMapping("info")
    public Result getInfo(@RequestParam String token) {

        Map<String, Object> map = userService.getUserInfo(token);
        return Result.ok().data(map).data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //登出
    @PostMapping("logout")
    public Result logout() {

        return Result.ok();
    }
    //获取所有用户
    @GetMapping("getAllUser")
    public Result getAllUser() {
        List<User> list = userService.list();
        return Result.ok().data("user", list);
    }
}

