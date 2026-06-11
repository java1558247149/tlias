package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void create(Emp emp);

    void del(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateData(Emp emp);

    List<Emp> getAllEmps();

    LoginInfo login(Emp emp);


    /*PageResult<Emp> page(Integer page, Integer pageSize, String name
            , Integer gender
            ,  LocalDate begin
            , LocalDate end);*/


}
