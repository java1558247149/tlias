package com.itheima.controller;

import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping("/log/page")
    public Result getLog( EmpQueryParam e){
        log.info("获取的数据为，{}", e);
        PageResult pageResult= logService.getLog(e);
        return Result.success(pageResult);
    }
}
