package com.jhc.dao;

import com.jhc.entity.Content;

import java.util.List;

public interface ContentDao {
    public List<Content> getContentAll(int offset, int num);//获取所有标注文本
}
