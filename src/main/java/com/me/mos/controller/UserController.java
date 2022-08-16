package com.me.mos.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.mos.common.Result;
import com.me.mos.pojo.User;
import com.me.mos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Maple
 * @Date 2022/8/12 16:05
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserService userService;


    @PostMapping
    public Result<?> save(@RequestBody User user){
        if (user.getPassword()==null){
            user.setPassword("123456");
        }
        userService.addUserByJson(user);
        return Result.success();
    }

    @GetMapping
    public Result<?> queryUsersToPageByKeyword(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String searchKey){
        Page<User> page=new Page<>(pageNum,pageSize);
        Page<User> userPage = userService.queryUserByKeyWord(page, searchKey);
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        userService.updateUserById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> userLogin(@RequestBody User user){
        User exist = userService.userLogin(user);
        if (exist!=null){
            return Result.success(exist);
        }else {
            return Result.error("-1", "登录失败");
        }
    }

    @PostMapping("/register")
    public Result<?> userRegister(@RequestBody User user){
        if (StrUtil.isBlank(user.getUsername())){
            return Result.error("-1","error");
        }
        boolean flag = userService.userRegister(user);
        if (flag){
            return Result.success();
        }else {
            return Result.error("-1", "用户名已经存在");
        }
    }

}
