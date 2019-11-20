package com.jhc.tools;

import com.jhc.dao.ContentDao;
import com.jhc.dao.ContentDaoImpl;
import com.jhc.dao.ResultDao;
import com.jhc.dao.ResultDaoImpl;
import com.jhc.entity.Content;
import com.jhc.entity.Interface;
import com.jhc.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetMixContent {

    public void getTable(){

        int interOffset = 0;//0 - 1620
        int userOffset = 0;//0 - 180
        Content content = new Content();
        List<Content> contentList = new ArrayList<>();//待存入数据库的内容

        ConnDB.init();
        System.out.println("开始读取混合型内容");

        //取出需要的混合型内容
        for(interOffset=0;interOffset<600;interOffset+=60){
            System.out.println("开始读取 interOffset: " + interOffset);
            for(userOffset=0;userOffset<180;userOffset++){
                content = getContentMix(interOffset,userOffset);
                contentList.add(content);
            }
            System.out.print(" ---- 已完成");
        }
        System.out.println("已取出全部混合型内容，开始写入数据库");

        //将内容存入数据库
        for(Content c : contentList){
            ConnDB.addUpdDel("insert into content_mix(contentId, probability, content, word_list)" + "values('" + c.getContentId()  + "','" + c.getProbability() + "','" + c.getContent() +"','" + c.getWordList() + "');");
        }
        System.out.println("数据库写入完成");

        ConnDB.closeConn();


    }

    //获取文本的敏感词集合
    public String getWordList(String contentdId){
        String str = "";
        try {
            ResultSet rs = ConnDB.selectSql("select content from content_word where probability > 0.6 and substring_index(wordId,'_',1) =" + contentdId);
            while(rs.next()){ ;
                str += rs.getString("content") + ",";
            }
            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Content getContentMix(int interOffset,int userOffset){
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
            ResultSet rs = ConnDB.selectSql("select * from content_frag limit " + offset + ", " + 1);
            while(rs.next()){
                content.setContentId(rs.getString("contentId"));
                content.setProbability(rs.getString("probability"));
                content.setContent(rs.getString("content"));
                content.setWordList(getWordList(rs.getString("contentId")));
            }
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
            ResultSet rs = ConnDB.selectSql("select * from content_sent where substring_index(sentId,'_',1) = " + contentdId + " order by probability DESC limit 1");
            while(rs.next()){
                sent.setContentId(rs.getString("sentId"));
                sent.setProbability(rs.getString("probability"));
                sent.setContent(rs.getString("content"));
                sent.setWordList(getWordList(contentdId));
            }
            return sent;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取文本的最敏感句及前后句
    //合并句子内容和Id
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
                ResultSet rs = ConnDB.selectSql("select * from content_sent where substring_index(sentId,'s',-1) = "+ sentIndex +" and substring_index(sentId,'-',1) = " + contentdId);
                while(rs.next()){
                    Content content = new Content();
                    content.setContentId(rs.getString("sentId"));
                    content.setProbability(rs.getString("probability"));
                    content.setContent(rs.getString("content"));
                    content.setWordList(getWordList(contentdId));
                    list.add(content);
                }
                sentIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sent.setContentId("");
        sent.setContent("");
        for(Content cont : list){
            if(sent.getContentId() == ""){
                sent.setContentId(cont.getContentId());
            }else{
                sent.setContentId(sent.getContentId() + "," + cont.getContentId());
            }
            sent.setContent(sent.getContent() + cont.getContent());
        }
        sent.setWordList(getWordList(contentdId));
        return sent;
    }

}
