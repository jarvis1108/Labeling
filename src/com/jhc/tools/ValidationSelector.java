package com.jhc.tools;



import com.jhc.entity.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ValidationSelector {



    public void select(){

        int offset;
        int count;
        int[] interfaces = new int[]{3, 6, 8, 10, 11, 13, 14, 15};//validation界面

        try {
            ConnDB.init();

            for(int i = 0;i<interfaces.length;i++){

                List<Integer> offsetList = new ArrayList<Integer>();
                //取出该种界面中标注用户数大于1的offset集合
                ResultSet rs = ConnDB.selectSql("select \n" +
                        "offset, count(*) as count\n" +
                        "from (select offset from user_interface WHERE interfaceId = "+ interfaces[i] + ") as allocation\n" +
                        "GROUP BY offset\n" +
                        "\n");
                while(rs.next()){
                    offset = rs.getInt("offset");
                    count = rs.getInt("count");
                    if(count > 1){
                        offsetList.add(offset);
                    }
                }

                //取出具有相同内容offset的用户
                String username;
                Map<String,String> map=new HashMap<>();//存储该种界面对应所有内容的最终标注结果

                for (int off : offsetList){
                    List<String> usernameList = new ArrayList<String>();

                    rs = ConnDB.selectSql("select username from user_interface where interfaceId = " + interfaces[i] + " and offset = " + off);
                    while(rs.next()){
                        username = rs.getString("username");
                        usernameList.add(username);
                    }

                    //取出每组用户的标注结果
                    String contentId;
                    String result;
                    String res;

                    for(String user:usernameList){
                        rs = ConnDB.selectSql("select distinct contentId, result from result where username = " + user);
                        while(rs.next()){
                            contentId = rs.getString("contentId");
                            result = rs.getString("result") ;

                            //检查是否有其他用户对该条内容的标注记录
                            if(map.containsKey(contentId)){
                                //若有，检查其他用户的标注结果与此结果是否相同
                                res = map.get(contentId);
                                if(!result.equals(res)){
                                    //若不相同，结果无效，用-1表示
                                    map.put(contentId,"-1");
                                }
                            } else {
                                map.put(contentId,result);
                            }
                        }
                        if(i>1)contentId = "";
                    }

                    if(i>1)
                        contentId = "";
                }

                //存储最终标注结果
                String contentId;
                String result;

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    contentId = entry.getKey();
                    result = entry.getValue();
                    ConnDB.addUpdDel("insert into result_validation(contentId, interfaceId, result) " +
                            "values('"+ contentId + "','"+ interfaces[i] + "','"+ result + "')");
                }

            }

            ConnDB.closeConn();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
