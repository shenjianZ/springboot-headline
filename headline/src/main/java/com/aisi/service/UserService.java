package com.aisi.service;

import com.aisi.entity.User;
import com.aisi.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jianyu
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-12-14 15:28:57
*/
public interface UserService extends IService<User> {

    /**
     * 登录功能接口实现
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据 token 来获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 查询用户名是否已占用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 用户注册的接口实现
     * @param user
     * @return
     */
    Result regist(User user);
}
