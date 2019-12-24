package com.banyechan.banyechan_escape_path.mapper;

import com.banyechan.banyechan_escape_path.entity.Edge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EdgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Edge record);

    int insertSelective(Edge record);

    Edge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Edge record);

    int updateByPrimaryKey(Edge record);

    List<Edge> listByMap(Map<String,Object> map);
}