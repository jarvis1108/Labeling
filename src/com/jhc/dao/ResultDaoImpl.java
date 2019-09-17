package com.jhc.dao;

import com.jhc.entity.Result;
import com.jhc.tools.ConnDB;

public class ResultDaoImpl implements ResultDao {
    public boolean submit(Result result){
        boolean flag = false;
        ConnDB.init();
        int i = ConnDB.addUpdDel("insert into result(username, contentId, interfaceId, result) " +
                "values('"+result.getUsername()+ "','"+result.getContentId()+ "','"+result.getInterfaceId()+ "','"+result.getResult()+ "')");
        if(i>0){
            flag = true;
        }
        ConnDB.closeConn();
        return flag;
    }
}
