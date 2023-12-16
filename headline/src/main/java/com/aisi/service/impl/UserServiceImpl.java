package com.aisi.service.impl;

import com.aisi.utils.JwtHelper;
import com.aisi.utils.MD5Util;
import com.aisi.utils.Result;
import com.aisi.utils.ResultCodeEnum;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aisi.entity.User;
import com.aisi.service.UserService;
import com.aisi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author jianyu
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-12-14 15:28:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper mapper;
    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 登录功能接口实现
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = mapper.selectOne(queryWrapper);
        if(loginUser==null)
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        if(!StringUtils.isEmpty(loginUser.getUserPwd())
            && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))){
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap();
            data.put("token",token);
            return Result.ok(data);
        }else {
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
    }

    /**
     * 根据 token 来获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        User user = mapper.selectById(userId);
        if(user==null)
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        else{
            Map data = new HashMap<>();
            user.setUserPwd("");
            data.put("loginUser",user);
            return Result.ok(data);
        }
    }

    /**
     * 查询用户名是否已占用
     *
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        Long count = mapper.selectCount(queryWrapper);
        if(count>0)
            return Result.build(null,ResultCodeEnum.USERNAME_USED);

        else
            return Result.ok(null);
    }

    /**
     * 用户注册的接口实现
     *
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = mapper.selectCount(queryWrapper);
        if(count>0)
            return  Result.build(null,ResultCodeEnum.USERNAME_USED);
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = mapper.insert(user);
        return Result.ok(null);
    }

}




