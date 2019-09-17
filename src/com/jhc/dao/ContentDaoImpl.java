package com.jhc.dao;

import com.jhc.entity.Content;
import com.jhc.tools.ConnDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {

    //获取所有待标注文本
    public List<Content> getContentAll(int offset, int num) {
        List<Content> list = new ArrayList<Content>();
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from content_frag limit " + offset + ", " + num);
            while(rs.next()){
                Content content = new Content();
                content.setContentId(rs.getInt("contentId"));
                content.setProbability(rs.getString("probability"));
                content.setContent(rs.getString("content"));
                list.add(content);
            }
            ConnDB.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
