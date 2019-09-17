package com.jhc.dao;

import java.util.List;

import com.jhc.entity.User;

public interface UserDao {
    public boolean register(User user);//注册
    public boolean login(String username,String password);//登录
    public User getUser(String username,String password);//获取用户

}
