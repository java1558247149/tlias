package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentListParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageResult<Student> getWhereStu(StudentListParam studentListParam) {
        PageHelper.startPage(studentListParam.getPage(), studentListParam.getPageSize());
        List<Student> list = studentMapper.getWhereStu(studentListParam);
        Page p = (Page) list;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    @Override
    public void createStu(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.createStu(student);
    }

    @Override
    public Student Idget(Integer id) {
        return studentMapper.Idget(id);
    }

    @Override
    public void setStu(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.setStu(student);
    }

    @Override
    public void delStu(List<Integer> ids) {
        studentMapper.delStu(ids);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void createVio(Integer id, Integer score) {
        Student student = studentMapper.getCountScore(id);
        Integer count = Integer.valueOf(student.getViolationCount());
        Integer scores = Integer.valueOf(student.getViolationScore());
        count= count + 1;
        scores = scores + score;
        studentMapper.createVio(count, scores, id);


    }
}
