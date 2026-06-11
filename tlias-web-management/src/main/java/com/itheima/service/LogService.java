package com.itheima.service;

import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

public interface LogService {
    PageResult getLog(EmpQueryParam e);
}
