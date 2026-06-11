package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    @GetMapping
    public Result getWhereCheck(ClazzListParam clazzListParam){
        log.info("接收到的参数为,{}", clazzListParam);
        PageResult<Clazz> pageResult = clazzService.getWhereCheck(clazzListParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("需要被删除的班级id为，{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    @PostMapping
    public Result createClazz(@RequestBody Clazz Clazz){
        log.info("增加的班级为，{}", Clazz);
        clazzService.createClazz(Clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getIdClazz(@PathVariable Integer id){
        log.info("查询的班级id为,{}", id);
        Clazz clazz = clazzService.getIdClazz(id);
        log.info("信息为，{}",clazz);
        return Result.success(clazz);
    }

    @PutMapping
    public Result setClazz(@RequestBody Clazz clazz){
        log.info("需要被修改的班级名称为,{}",clazz);
        clazzService.setClazz(clazz);
        return  Result.success();
    }

    @GetMapping("/list")
    public Result getAllClazzs(){
        List<Clazz> list = clazzService.getAllClazzs();
        return Result.success(list);
    }
}
