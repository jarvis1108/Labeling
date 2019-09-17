package com.jhc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jhc.entity.User;
import com.jhc.tools.ConnDB;

public class UserDaoImpl implements UserDao{

    public boolean register(User user) {
        boolean flag = false;
        ConnDB.init();
        int i = ConnDB.addUpdDel("insert into user(username, password, sex, age, education, profession, labeling_exp, reading_exp, interfaceId) " +
                "values('"+user.getUsername()+"','"+user.getPassword()+ "','"+user.getSex()+ "','"+user.getAge()+ "','"+user.getEducation()+ "','"+user.getProfession()+ "','"+user.getLabeling_exp()+ "','"+user.getReading_exp()+ "','"+user.getInterfaceId()+"')");
        if(i>0){
             flag = true;
        }
        ConnDB.closeConn();
        return flag;
    }

    public boolean login(String username, String password) {
        boolean flag = false;
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from user where username='"+username+"' and password='"+password+"'");
            while(rs.next()){
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                    flag = true;
                }
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User getUser(String username, String password){
        User user = new User();
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from user where username='"+username+"' and password='"+password+"'");
            while(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getString("age"));
                user.setEducation(rs.getString("education"));
                user.setProfession(rs.getString("profession"));
                user.setLabeling_exp(rs.getString("labeling_exp"));
                user.setReading_exp(rs.getString("reading_exp"));
                user.setInterfaceId(rs.getInt("interfaceId"));
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}

