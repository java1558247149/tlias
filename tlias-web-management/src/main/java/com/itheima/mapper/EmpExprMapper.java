package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void exprs(List<EmpExpr> empExprs);

    void delEmpIds(List<Integer> empIds);
}
