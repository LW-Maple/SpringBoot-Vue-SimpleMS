package com.me.mos.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.mos.mapper.UserMapper;
import com.me.mos.pojo.User;
import com.me.mos.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.function.BiConsumer;

/**
 * @Author Maple
 * @Date 2022/8/12 16:30
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUserByJson(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Page<User> queryUserByKeyWord(Page<User> page, String keyword) {
        LambdaQueryWrapper<User> userWrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(keyword)){
            userWrapper.like(User::getNickname,keyword);
        }
        Page<User> userPage = userMapper.selectPage(page, userWrapper);
        return userPage;
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User userLogin(User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        return res;
    }

    @Override
    public boolean userRegister(User user) {
        User selectOne = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (selectOne!=null){
            return false;
        }else {
            addUserByJson(user);
            return true;
        }
    }
}
