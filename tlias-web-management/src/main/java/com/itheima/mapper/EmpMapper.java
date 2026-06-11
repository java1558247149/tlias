package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    // ------------------------原始方法实现--------------------------

    /*@Select("select count(*) from emp e, dept d where e.dept_id = d.id")
    public long count();

    @Select("select e.*, d.name deptName from emp e, dept d where e.dept_id = d.id order by d.update_time desc limit #{page},#{pageSize}")
    public List<Emp> page(Integer page, Integer pageSize);*/

    //@Select("select e.*, d.name deptName from emp e, dept d where e.dept_id = d.id order by d.update_time desc ")
    public List<Emp> page(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            " values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime} )")
    void create(Emp emp);

    void delIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateData(Emp emp);

    @MapKey(value = "pos")
    List<Map<String, Object>> getData();

    @MapKey(value = "name")
    List<Map<String, Object>> getGenderData();

    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> getAllEmps();

    @Select("select * from emp where dept_id = #{id}")
    List<Integer> getDelId(Integer id);

    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp getUserNameAndPassword(Emp emp);
}
