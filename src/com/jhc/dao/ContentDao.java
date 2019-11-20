package com.jhc.dao;

import com.jhc.entity.Content;

import java.util.List;

public interface ContentDao {
    public List<Content> getContentAll(int offset, int num);//获取所有文本
    public List<Content> getContentMixAll(int offset, int num);//获取所有混合文本
    public Content getContent(int offset);//获取下一条文本
    public Content getContentMix(int interOffset,int userOffset);//获取下一条混合文本
}
