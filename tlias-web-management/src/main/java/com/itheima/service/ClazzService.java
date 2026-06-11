package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzListParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> getWhereCheck(ClazzListParam clazzListParam);

    void deleteClazz(Integer id);

    void createClazz(Clazz clazz);

    Clazz getIdClazz(Integer id);

    void setClazz(Clazz clazz);

    List<Clazz> getAllClazzs();
}
