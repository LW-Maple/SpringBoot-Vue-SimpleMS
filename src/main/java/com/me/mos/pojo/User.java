package com.me.mos.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户类
 *
 * @Author Maple
 * @Date 2022/8/12 16:08
 * @Version 1.0
 */

@TableName("t_user")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private String sex;
    private String address;

}
