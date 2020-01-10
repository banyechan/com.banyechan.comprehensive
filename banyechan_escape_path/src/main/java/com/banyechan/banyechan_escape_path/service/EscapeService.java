package com.banyechan.banyechan_escape_path.service;

import com.banyechan.banyechan_escape_path.entity.Vertex;

import java.util.List;
import java.util.Map;

public interface EscapeService {

    String getEscapePath(Integer id,Integer fireId);

    Integer getNearestVertexId(Vertex record);

    List<Map<String,Object>> listMapTest(Integer state);
}
