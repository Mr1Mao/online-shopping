package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    //用户登录接口
    public User getUserBy(String username);
    //用户注册接口
    public int register(User user);
    //修改用户余额
    public int property(double Property ,int u_id);
}
