package com.aisi.controller;

import com.aisi.entity.User;
import com.aisi.service.UserService;
import com.aisi.utils.JwtHelper;
import com.aisi.utils.Result;
import com.aisi.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     *登录业务接口实现
     * @param user  请求参数为 JSON类型
     * @return   返回值为 JSON类型
     */
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = service.login(user);
        return result;
    }

    /**
     * 根据 token来获取用户信息
     * @param token 请求参数 为 Header 类型
     * @return  返回值为 JSON类型
     */
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = service.getUserInfo(token);
        return result;
    }

    /**
     * 查询用户名是否已占用
     * @param username 请求参数为 param 类型
     * @return  返回值为 JSON类型
     */
    @GetMapping("checkUserName")
    public Result checkUserName(@RequestParam String username){
        Result result = service.checkUserName(username);
        return result;
    }

    /**
     * 用户注册的接口实现
     * @param user  请求参数为 param 类型
     * @return  返回值为 JSON类型
     */
    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result = service.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        Result result = null;
        if(jwtHelper.isExpiration(token))
            result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        else
            result = Result.build(null,ResultCodeEnum.SUCCESS);
        return result;
    }


}
