package com.itheima.service;

import com.itheima.newException.DelException;
import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    public List<Dept> findAll();

    public void deteleById(Integer id) throws DelException;

    void add(Dept dept);

    Dept sle(Integer id);

    void update(Dept dept);
}
