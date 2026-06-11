package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentListParam;

import java.util.List;
import java.util.Map;

public interface StudentService {


    PageResult<Student> getWhereStu(StudentListParam studentListParam);

    void createStu(Student student);

    Student Idget(Integer id);

    void setStu(Student student);

    void delStu(List<Integer> ids);

    void createVio(Integer id, Integer score);
}
