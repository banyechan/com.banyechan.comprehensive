package com.banyechan.banyechan_escape_path.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Edge {
    private Integer id;

    private Integer pointId;

    private Integer endPointId;

    private Double distance;

    private Date createTime;

    private Date updateTime;

    private Edge next;      //邻接表的下一条边
}