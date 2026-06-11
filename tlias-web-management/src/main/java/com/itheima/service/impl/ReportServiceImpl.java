package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzCountOption;
import com.itheima.pojo.ClazzListParam;
import com.itheima.pojo.JobOption;
import com.itheima.service.EmpService;
import com.itheima.service.ReportService;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    EmpMapper empMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public JobOption getData() {
        //1.获取数据到一个集合中
        List<Map<String, Object>> list = empMapper.getData();
        //2.将数据分类，封装到类中
        List<Object> jobName = list.stream().map(jobArr -> jobArr.get("pos")).toList();
        List<Object> data = list.stream().map(jobArr -> jobArr.get("num")).toList();
        return new JobOption(jobName, data);

    }

    @Override
    public List<Map<String, Object>> getGenderData() {
        return empMapper.getGenderData();
    }

    @Override
    public ClazzCountOption getClazzCount() {
        List<Map<String, Object>> list = clazzMapper.getClazzCount();
        List<Object> pos = list.stream().map(s -> s.get("pos")).toList();
        List<Object> num = list.stream().map(s -> s.get("num")).toList();
        return new ClazzCountOption(pos, num);

    }

    @Override
    public List<Map<String, Object>> getDegreeData() {
        return studentMapper.getDegreeData();
    }
}
