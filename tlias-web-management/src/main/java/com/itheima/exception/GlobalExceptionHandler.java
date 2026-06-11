package com.itheima.exception;

import com.itheima.newException.DelException;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result HandlerException(Exception e){
        log.error("异常为", e);
        return Result.error("出错了，找管理员");
    }

    @ExceptionHandler
    public Result HandlerDuplicateException(DuplicateKeyException e){
        log.error("异常为", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate");
        String str = message.substring(i);
        String[] arr = str.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    @ExceptionHandler
    public Result HandlerdelException(DelException D){
        return Result.error("对不起，当前部门下有员工，不能直接删除！");
    }
}
