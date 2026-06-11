package com.itheima.controller;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

   /* @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer pageSize
            , String name
            , Integer gender
            , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin
            , @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    ){
        log.info("前端返回{},{},{},{},{},{}" , page , pageSize , name, gender, begin, end);
        PageResult<Emp> result = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(result);
    }*/
   @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("前端返回{}" , empQueryParam);
        PageResult<Emp> result = empService.page(empQueryParam);
        return Result.success(result);
    }

    @PostMapping
    public Result create(@RequestBody Emp emp){
       empService.create(emp);
       return Result.success();
    }

    /*@DeleteMapping
    public Result del(Integer[] arr){
       log.info("存储的数据为,{}", Arrays.toString(arr));
       return Result.success();
    }*/
    @DeleteMapping
    public Result del(@RequestParam List<Integer> ids){
       log.info("存储的数据为,{}", ids);
       empService.del(ids);
       return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("获取的id为,{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result updateData(@RequestBody Emp emp){
        log.info("修改后 用户的信息为{}", emp);
        empService.updateData(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAllEmps(){
        log.info("开始查询所有员工");
        List<Emp> list = empService.getAllEmps();
        return Result.success(list);
    }
}
