package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentListParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping
    public Result getWhereStu(StudentListParam studentListParam){
        log.info("接收的参数为，{}", studentListParam);
        PageResult<Student> pageResult = studentService.getWhereStu(studentListParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result createStu(@RequestBody Student student){
        log.info("接收到的学生信息为，{}", student);
        studentService.createStu(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result Idget(@PathVariable Integer id){
        log.info("获取的id为,{}", id);
        Student student = studentService.Idget(id);
        return Result.success(student);
    }
    @PutMapping
    public Result setStu(@RequestBody Student student){
        log.info("被修改的学生信息为，{}", student);
        studentService.setStu(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delStu(@PathVariable List<Integer> ids){
        log.info("需要删除的学生id为，{}", ids);
        studentService.delStu(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result createVio(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪学生的id和扣分情况分别为，{}，{}", id, score);
        studentService.createVio(id, score);
        return Result.success();
    }



}
