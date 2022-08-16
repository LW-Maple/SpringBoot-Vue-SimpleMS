package com.me.mos.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.me.mos.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Author Maple
 * @Date 2022/8/12 16:17
 * @Version 1.0
 */

public interface UserService {

    int addUserByJson(User user);

    Page<User> queryUserByKeyWord(Page<User> page,String keyword);

    int updateUserById(User user);

    int deleteUserById(int id);

    User userLogin(User user);

    boolean userRegister(User user);

}
