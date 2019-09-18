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
                content.setContentId(rs.getString("contentId"));
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

    //获取具有context变量的混合文本
    public List<Content> getContentMix(int offset, int num){
        List<Content> contentList = new ArrayList<Content>();
        List<Content> sentList = new ArrayList<Content>();
        List<Content> resList = new ArrayList<Content>();
        //获取文本
        contentList = getContentAll(offset, num);

//        ConnDB.init();
        //获取文本的最敏感句
        for(Content cont :contentList){
            Content sent = getSent(cont.getContentId());
            sentList.add(sent);
        }
        //获取文本的最敏感句及前后句
        for(Content cont :contentList){
            Content sent = getSentWithNeibor(cont.getContentId());
            resList.add(sent);
        }
//        ConnDB.closeConn();


        //混合（单句/单句+前后句/段落 1:1:1 ）
        resList.addAll(sentList);
        resList.addAll(sentList);
        return resList;
    }

    //获取文本的最敏感句
    private Content getSent(String contentdId){
        Content sent = new Content();
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from content_sent where substring_index(sentId,'_',1) = " + contentdId + " order by probability DESC limit 1");
            while(rs.next()){
                sent.setContentId(rs.getString("sentId"));
                sent.setProbability(rs.getString("probability"));
                sent.setContent(rs.getString("content"));
            }
            ConnDB.closeConn();
            return sent;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取文本的最敏感句及前后句
    //合并句子内容，保留最敏感句Id
    private Content getSentWithNeibor(String contentdId){
        List<Content> list = new ArrayList<Content>();
        Content res = new Content();
        //获取文本的最敏感句序号
        Content sent = getSent(contentdId);
        int sentIndex = Integer.parseInt(sent.getContentId().split("s")[1]);
        //若最敏感句不是首句则将sentIndex置为前句
        if(sentIndex > 0)
            sentIndex -= 1;

        try {
            for(int i=0;i<3;i++){
                ConnDB.init();
                ResultSet rs = ConnDB.selectSql("select * from content_sent where substring_index(sentId,'s',-1) = "+ sentIndex +" and substring_index(sentId,'-',1) = " + contentdId);
                while(rs.next()){
                    Content content = new Content();
                    content.setContentId(rs.getString("sentId"));
                    content.setProbability(rs.getString("probability"));
                    content.setContent(rs.getString("content"));
                    list.add(content);
                }
                sentIndex++;
                ConnDB.closeConn();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Content cont : list){
            res.setContent(res.getContent() + cont.getContent());
        }
        return res;
    }

    //获取文本的词语集合
    private List<Content> getWordList(String contentdId){
        List<Content> list = new ArrayList<Content>();
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from content_word where substring_index(wordId,'_',1) =" + contentdId);
            while(rs.next()){
                Content content = new Content();
                content.setContentId(rs.getString("wordId"));
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

    //

}
