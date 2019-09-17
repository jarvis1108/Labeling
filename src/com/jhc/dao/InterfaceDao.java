package com.jhc.dao;

public interface InterfaceDao {
    public boolean allocateInterfaceToUser(String username);//为用户分配可用界面
    public int getInterfaceIdByUser(String username);//获取分配给用户的界面序号
}
