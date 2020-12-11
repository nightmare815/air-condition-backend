package com.bin.aircondition.service;

import com.bin.aircondition.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-11-01
 */
public interface UserService extends IService<User> {

    //登录
    String login(User user);

    //获取登录信息
    Map<String, Object> getUserInfo(String token);
}
