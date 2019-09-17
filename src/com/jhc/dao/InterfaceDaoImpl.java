package com.jhc.dao;

import com.jhc.entity.Interface;
import com.jhc.tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceDaoImpl implements InterfaceDao {

    public int getAvailableInterface(){
        int flag = 0;
        try{
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from interface");
            while(rs.next()){
                int interfaceId = rs.getInt("interfaceId");
                int userTotal = rs.getInt("userTotal");
                //统计result表中该种界面的用户数
                ResultSet rs2 = ConnDB.selectSql("select count(interfaceId) as userCurrent from result where interfaceId = " + interfaceId);
                int userCurrent = rs2.getInt("userCurrent");
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

}
