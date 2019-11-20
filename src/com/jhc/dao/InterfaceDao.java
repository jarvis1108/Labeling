package com.jhc.dao;

import com.jhc.entity.Interface;

public interface InterfaceDao {
    public boolean allocateInterfaceToUser(String username);//为用户分配可用界面
    public Interface getInterface(String username);//获取分配给用户的界面
    public boolean validExpUser(String username);//监督实验：激活用户
}
