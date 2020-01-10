package com.banyechan.banyechan_escape_path.mapper;

import com.banyechan.banyechan_escape_path.entity.Vertex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VertexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vertex record);

    int insertSelective(Vertex record);

    Vertex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vertex record);

    int updateByPrimaryKey(Vertex record);

    List<Vertex> listByMap(Map<String,Object> map);
}