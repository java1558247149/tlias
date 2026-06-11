package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.LogOperation;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzListParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> getWhereCheck(ClazzListParam clazzListParam) {
        Integer page = clazzListParam.getPage();
        Integer pageSize = clazzListParam.getPageSize();
        PageHelper.startPage(page, pageSize);
        List<Clazz> list = clazzMapper.getWhereCheck(clazzListParam);

        for (Clazz clazz : list) {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if (LocalDate.now().isAfter(beginDate) && LocalDate.now().isBefore(endDate)){
                clazz.setStatus("已开班");
            }else if(LocalDate.now().isBefore(beginDate)){
                clazz.setStatus("未开班");
            }else{
                clazz.setStatus("已结课");
            }
        }
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public void createClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.createClazz(clazz);
    }

    @Override
    public Clazz getIdClazz(Integer id) {
        return clazzMapper.getIdClazz(id);
    }

    @Override
    public void setClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.setClazz(clazz);
    }

    @Override
    public List<Clazz> getAllClazzs() {
       return clazzMapper.getAllClazzs();
    }
}
