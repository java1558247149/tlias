package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.LogOperation;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    /*@Override
    public PageResult<Emp> page(Integer pageNum, Integer pageSize
            , String name
            , Integer gender
            , LocalDate begin
            , LocalDate end) {
        PageHelper.startPage(pageNum,pageSize);
        List<Emp> empList = empMapper.page(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.page(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void create(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.create(emp);
        List<EmpExpr> empExprs = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprs)) {
            empExprs.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.exprs(empExprs);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void del(List<Integer> ids) {
        //删除员工表里面的记录
        empMapper.delIds(ids);
        //删除员工经历表里面的记录
        empExprMapper.delEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateData(Emp emp) {
        //1.修改员工表
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateData(emp);


        //2.修改员工经历表
        //2.1删除员工经历表

        empExprMapper.delEmpIds(Arrays.asList(emp.getId()));

        //2.2增加员工经历表
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(e ->
                    e.setEmpId(emp.getId()));
            empExprMapper.exprs(exprList);
        }
    }

    @Override
    public List<Emp> getAllEmps() {
        return empMapper.getAllEmps();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.getUserNameAndPassword(emp);
        if (e != null){
            log.info("登录成功，用户信息为{}", e);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String s = JwtsUtil.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), s);
        }

        return null;
    }
}
