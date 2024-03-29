package com.jhc.dao;

import com.jhc.entity.Result;
import com.jhc.tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDaoImpl implements ResultDao {
    public boolean submit(Result result){
        boolean flag = false;
        ConnDB.init();
        int i = ConnDB.addUpdDel("insert into result(username, contentId, interfaceId, result, highlight_text, cost) " +
                "values('"+result.getUsername()+ "','"+result.getContentId()+ "','"+result.getInterfaceId()+ "','"+result.getResult()+ "','"+result.getHighlight_text()+ "','"+result.getCost()+ "')");
        if(i>0){
            flag = true;
        }
        ConnDB.closeConn();
        return flag;
    }

    public int countResultsByInterfaceId(int interfaceId){
        int number = -1;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select count(interfaceId) as number from result where interfaceId = " + interfaceId);
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

    public int countResultsByUsername(String  username){
        int number = -1;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select count(contentId) as number from result where username = " + username);
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

    public int countResultsByUsernameAndInterfaceId(String  username, int interfaceId){
        int number = -1;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select count(contentId) as number from result where username = " + username + " and interfaceId = " + interfaceId);
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
}
