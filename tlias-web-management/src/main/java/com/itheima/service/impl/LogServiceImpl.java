package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.LogMapper;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.Logs;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;
    @Override
    public PageResult getLog(EmpQueryParam e) {
        PageHelper.startPage(e.getPage(),e.getPageSize());
        List<Logs>list = logMapper.page();
        Page<Logs> list1 = (Page<Logs>) list;
        return new PageResult(list1.getTotal(),list1.getResult());
    }
}
