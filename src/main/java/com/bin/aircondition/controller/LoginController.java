package com.bin.aircondition.controller;

import com.bin.aircondition.commonutils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/aircondition/user")
public class LoginController {

    //登录
    @PostMapping("login")
    public Result login() {

        return Result.ok().data("token", "admin");
    }

    //根据token获取用户信息
    @GetMapping("info")
    public Result getInfo() {

        return Result.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");    }

    //登出
    @PostMapping("logout")
    public Result logout() {

        return Result.ok().data("token", "admin");
    }
}
