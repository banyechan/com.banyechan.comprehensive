package com.banyechan.comprehensive.banyechan_tomcat_war.mapper;

import com.banyechan.comprehensive.banyechan_tomcat_war.entity.StudentModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentModel record);

    int insertSelective(StudentModel record);

    StudentModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentModel record);

    int updateByPrimaryKey(StudentModel record);
}