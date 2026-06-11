package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.newException.DelException;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result findAll(){
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    /*@DeleteMapping("/depts")
    public Result del(@RequestParam(value = "id",required = false) Integer did){
        System.out.println("数据为" + did);
        return Result.success();
    }*/
    @LogOperation
    @DeleteMapping("/depts")
    public Result del(Integer id) throws DelException {
        System.out.println("数据为" + id);
        deptService.deteleById(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping("/depts")
    public Result insert(@RequestBody Dept dept){
        log.info("{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @LogOperation
    @GetMapping("/depts/{id}")
    public Result sle(@PathVariable Integer id){
        log.info("{}",id);
        Dept dept = deptService.sle(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("{}",dept);
        return Result.success();
    }
}
