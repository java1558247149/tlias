package com.itheima.mapper;

import com.itheima.pojo.Logs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {

    List<Logs> page();
}
