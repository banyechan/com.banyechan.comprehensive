package com.banyechan.banyechan_escape_path.entity;

import java.util.ArrayList;

public class Graph {

    final int MAX_VERTEX_NUM=120;   //最大节点数
    final int MAX_EDGE_NUM=120;   //最大边数
    final Double INFINITY=65565.0;  //表示无穷大


    public int vertexNum;  //顶点数
    public int edgeNum;    //边数
    public Vertex[] vertexList;  // 顶点数组

    //public boolean[] visited;  // 判断顶点是否被访问过

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }
//
//    public Vertex[] getvertexList() {
//        return vertexList;
//    }
//
//    public void setvertexList(Vertex[] vertexList) {
//        this.vertexList = vertexList;
//    }
//
//    public boolean[] getVisited() {
//        return visited;
//    }
//
//    public void setVisited(boolean[] visited) {
//        this.visited = visited;
//    }

    public void creatGraph(ArrayList<Vertex> vexs, int[][] edges){
        //初始化顶点数和边数
        vertexNum=vexs.size();
        edgeNum=edges.length;

        //初始化顶点
        vertexList=new Vertex[vertexNum];
        for(int i=0;i<vertexList.length;i++){
            vertexList[i]=vexs.get(i);
            vertexList[i].firstedge=null;
            System.out.println(vertexList[i].getPoint());
        }
        //初始化边
        for(int i=0;i<edgeNum;i++){
            //获取边的起始顶点和结束顶点
            int p1=edges[i][0];
            int p2=edges[i][1];
            Double weight=(double)edges[i][2];
            System.out.println(p1+"-"+p2);
            //生成边节点
            Edge e1=new Edge();
            e1.setEndPointId(p2);
            e1.setDistance(weight);
            //把边节点添加到起始顶点的链表中
            if(vertexList[p1].firstedge==null){
                vertexList[p1].firstedge=e1;
            }else{
                linkLast(vertexList[p1].firstedge,e1);
            }
            //同样把边节点添加到结束顶点的链表中
            Edge e2=new Edge();
            e2.setEndPointId(p1);
            e2.setDistance(weight);
            if(vertexList[p2].firstedge!=null){
                linkLast(vertexList[p2].firstedge,e2);
            }else{
                vertexList[p2].firstedge=e2;
            }
        }
    }
    /**
     * 把边节点链接在最后
     * @param beg   顶点节点指向的第一个边节点
     * @param end   要加入的边节点
     */
    public void linkLast(Edge beg,Edge end){
        Edge p=beg;
        while(p.getNext()!=null){
            p=p.getNext();
        }
        p.setNext(end);
    }
//    public void print() {
//        System.out.printf("List Graph:\n");
//        for (int i = 0; i < vertexList.length; i++) {
//            System.out.printf("%d(%s): ", i, vertexList[i].getPoint());
//            Edge node = vertexList[i].firstedge;
//            while (node != null) {
//                System.out.printf("%d(%s)路程[%.1f] ", node.getEndPointId(), vertexList[node.adjvertex].name,node.value);
//                node = node.next;
//            }
//            System.out.printf("\n");
//        }
//    }










}
