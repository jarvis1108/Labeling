package com.jhc.dao;

import com.jhc.entity.Result;

public interface ResultDao {
    public boolean submit(Result result);//提交一条标注结果
    public int countResultsByInterfaceId(int interfaceId);//统计某种界面的标注结果数
    public int countResultsByUsername(String username);//统计某个用户的标注结果数
}
