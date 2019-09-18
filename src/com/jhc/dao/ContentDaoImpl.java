package com.jhc.dao;

import com.jhc.entity.Content;
import com.jhc.tools.ConnDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {

    //获取文本的敏感词集合
    public List<String> getWordList(String contentdId){
        List<String> list = new ArrayList<String>();
        String str;
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select content from content_word where probability > 0.4 and substring_index(wordId,'_',1) =" + contentdId);
            while(rs.next()){ ;
                str = rs.getString("content");
                list.add(str);
            }
            ConnDB.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取所有文本
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
                content.setWordList(getWordList(rs.getString("contentId")));
                list.add(content);
            }
            ConnDB.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取一条混合文本（单句/单句+前后句/段落 1:1:1 ）
    public Content getContentMix(int interOffset,int userOffset){
        Content content = new Content();
        Content res = new Content();
        int offset = interOffset + userOffset;

        //120-180获取文本
        if(userOffset>=120){
            content = getContent(offset-120);
            return content;
        }
        //60-119获取文本的最敏感句及前后句
        else if(userOffset>=60 && userOffset<120){
            content = getContent(offset-60);
            res = getSentWithNeibor(content.getContentId());
            return res;
        }
        //0-59获取文本的最敏感句
        else if(userOffset<60){
            content = getContent(offset);
            res = getSent(content.getContentId());
            return res;
        }
        return res;
    }

    //获取一条文本
    public Content getContent(int offset) {
        Content content = new Content();
        try {
            ConnDB.init();
            ResultSet rs = ConnDB.selectSql("select * from content_frag limit " + offset + ", " + 1);
            while(rs.next()){
                content.setContentId(rs.getString("contentId"));
                content.setProbability(rs.getString("probability"));
                content.setContent(rs.getString("content"));
                content.setWordList(getWordList(rs.getString("contentId")));
            }
            ConnDB.closeConn();
            return content;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                sent.setWordList(getWordList(contentdId));
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

        sent.setContent("");
        for(Content cont : list){
            sent.setContent(sent.getContent() + cont.getContent());
        }
        sent.setWordList(getWordList(contentdId));
        return sent;
    }

}
