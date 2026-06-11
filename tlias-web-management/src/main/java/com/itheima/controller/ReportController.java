package com.itheima.controller;

import com.itheima.pojo.ClazzCountOption;
import com.itheima.pojo.ClazzListParam;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/empJobData")
    public Result getData(){
        JobOption jobOption = reportService.getData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getGenderData(){
        log.info("进入controller");
        List<Map<String, Object>> list = reportService.getGenderData();
        return Result.success(list);
    }

    @GetMapping("/studentCountData")
    public Result getClazzCount(){
        log.info("进入controller");
        ClazzCountOption clazzCountOption = reportService.getClazzCount();
        return Result.success(clazzCountOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getDegreeData(){
        log.info("进入controller");
        List<Map<String, Object>> list = reportService.getDegreeData();
        return Result.success(list);
    }
}
