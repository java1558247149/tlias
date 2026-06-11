package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzListParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {


    List<Clazz> getWhereCheck(ClazzListParam clazzListParam);
    @Delete("delete from clazz where id=#{id}")
    void deleteClazz(Integer id);


    @Insert("insert into clazz(id, name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{id}, #{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject},#{createTime}, #{updateTime})")
    void createClazz(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id=#{id}")
    Clazz getIdClazz(Integer id);

    void setClazz(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz;")
    List<Clazz> getAllClazzs();

    @MapKey("pos")
    List<Map<String, Object>> getClazzCount();
}
