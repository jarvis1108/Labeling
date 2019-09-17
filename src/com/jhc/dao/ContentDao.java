package com.jhc.dao;

import com.jhc.entity.Content;

import java.util.List;

public interface ContentDao {
    public Content getContent();//返回一条文本
    public List<Content> getContentAll();//返回全部待标注文本
}
