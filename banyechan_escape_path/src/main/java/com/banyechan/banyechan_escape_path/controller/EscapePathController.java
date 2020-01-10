package com.banyechan.banyechan_escape_path.controller;

import com.banyechan.banyechan_escape_path.entity.Vertex;
import com.banyechan.banyechan_escape_path.service.EscapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/escape")
public class EscapePathController {

    @Autowired
    private EscapeService escapeService;

    @RequestMapping("/path")
    public String getEscapePath(Integer id,Integer fireId){
        String path = escapeService.getEscapePath(id,fireId);
        return  path;
    }


    @RequestMapping("/nearest_id")
    public Integer getNearestVertexId(Vertex point){




        return 0;
    }


    @RequestMapping("/test")
    public Integer getTest(Integer state){

        List<Map<String,Object>> result = escapeService.listMapTest(state);

        return result.size();
    }


}
