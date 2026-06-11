package com.itheima.service;

import com.itheima.pojo.ClazzCountOption;
import com.itheima.pojo.ClazzListParam;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getData();

    List<Map<String, Object>> getGenderData();

    ClazzCountOption getClazzCount();

    List<Map<String, Object>> getDegreeData();
}
