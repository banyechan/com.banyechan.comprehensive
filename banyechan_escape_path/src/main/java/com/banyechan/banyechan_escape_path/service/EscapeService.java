package com.banyechan.banyechan_escape_path.service;

import com.banyechan.banyechan_escape_path.entity.Vertex;

public interface EscapeService {

    String getEscapePath(Integer id,Integer fireId);

    Integer getNearestVertexId(Vertex record);

}
