package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentListParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> getWhereStu(StudentListParam studentListParam);

    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time)\n" +
            "                    values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime} )")
    void createStu(Student student);

    @Select("select * from student where id=#{id}")
    Student Idget(Integer id);

    void setStu(Student student);

    void delStu(List<Integer> ids);

    @Select("select violation_count, violation_score from student where id=#{id}")
    Student getCountScore(Integer id);

    @Update("update student set violation_count = #{count},violation_score=#{scores} where id=#{id} ")
    void createVio(Integer count, Integer scores, Integer id);

    @MapKey("name")
    List<Map<String, Object>> getDegreeData();
}
