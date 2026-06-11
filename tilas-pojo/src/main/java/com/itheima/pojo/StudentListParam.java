package com.itheima.pojo;

import lombok.Data;

@Data
public class StudentListParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page;
    private Integer pageSize;
}
