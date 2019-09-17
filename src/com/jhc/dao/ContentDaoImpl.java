package com.jhc.dao;

import com.jhc.entity.Content;
import com.jhc.tools.ConnDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {

    public Content getContent() {
        //TODO:随机选取
        Content content = new Content();
        try {
            ConnDB.init();
            int offset = 0;//限制了只取前5条数据
            int num = 1;
            ResultSet rs = ConnDB.selectSql("select * from content_frag limit " + offset + ", " + num);
            while(rs.next()){
                content.setContentId(rs.getInt("contentId"));
                content.setProbability(rs.getString("probability"));
                content.setContent(rs.getString("content"));
            }
            ConnDB.closeConn();
            return content;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Content> getContentAll() {
        List<Content> list = new ArrayList<Content>();
        try {
            ConnDB.init();
            int offset = 0;//限制了只取前5条数据
            int num = 5;
            ResultSet rs = ConnDB.selectSql("select * from text limit " + offset + ", " + num);
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
