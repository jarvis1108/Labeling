package com.jhc.dao;

import com.jhc.entity.Interface;
import com.jhc.tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceDaoImpl implements InterfaceDao {

    //为用户分配可用界面
    public boolean allocateInterfaceToUser(String username){
        boolean flag = false;
        int interfaceId = this.getAvailableInterface();
        int number = this.getUserPageByInterfaceId(interfaceId);
        int userCurrent = this.countUsersByInterfaceId(interfaceId);
        int offset = 0;
        if(userCurrent < 10){
            offset = userCurrent * number;
        }else{
            //对具有validation变量的接口，需要重复分配内容
            offset = (userCurrent - 10) * number;
        }

        ConnDB.init();
        int i = ConnDB.addUpdDel("insert into user_interface(username, interfaceId, offset, number) " +
                "values('"+ username +"','"+ interfaceId + "','"+ offset + "','" + number + "')");
        if(i>0){
            flag = true;
        }
        ConnDB.closeConn();
        return flag;
    }

    //获取分配给用户的界面
    public Interface getInterface(String username){
        Interface inter = new Interface();
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from user_interface where username = " + username);
            while(rs.next()){
                inter.setUsername(username);
                inter.setInterfaceId(rs.getInt("interfaceId"));
                inter.setOffset(rs.getInt("offset"));
                inter.setNumber(rs.getInt("number"));
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inter;
    }

    //获取可用界面序号，成功返回序号，失败返回0
    private int getAvailableInterface(){
        int flag = 0;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from interface");
            while(rs.next()){
                int interfaceId = rs.getInt("interfaceId");
                int userTotal = rs.getInt("userTotal");
                //统计interface_user表中该种界面的用户数
                int userCurrent = this.countUsersByInterfaceId(interfaceId);
                if(userCurrent < userTotal){
                    return interfaceId;
                }
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //统计界面现有用户数，成功返回数目，失败返回-1
    private int countUsersByInterfaceId(int interfaceId){
        int number = -1;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select count(interfaceId) as number from user_interface where interfaceId = " + interfaceId);
            while(rs.next()){
                number = rs.getInt("number");
                return number;
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    //获取界面对应的每人总页数，成功返回数目，失败返回-1
    private int getUserPageByInterfaceId(int interfaceId){
        int number = -1;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select userPage from interface where interfaceId = " + interfaceId);
            while(rs.next()){
                number = rs.getInt("userPage");
                return number;
            }
            ConnDB.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }



}
