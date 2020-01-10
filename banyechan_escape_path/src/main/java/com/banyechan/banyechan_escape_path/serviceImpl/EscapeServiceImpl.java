package com.banyechan.banyechan_escape_path.serviceImpl;

import com.banyechan.banyechan_escape_path.entity.Edge;
import com.banyechan.banyechan_escape_path.entity.Graph;
import com.banyechan.banyechan_escape_path.entity.Vertex;
import com.banyechan.banyechan_escape_path.mapper.EdgeMapper;
import com.banyechan.banyechan_escape_path.mapper.VertexMapper;
import com.banyechan.banyechan_escape_path.service.EscapeService;
import com.banyechan.banyechan_escape_path.utills.GPSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EscapeServiceImpl implements EscapeService {

    @Autowired
    private VertexMapper vertexMapper;
    @Autowired
    private EdgeMapper edgeMapper;

    @Override
    public String getEscapePath(Integer startId, Integer fireId) {
        Graph graph = creatGraph();
        if (startId < 0 || startId >= graph.vertexNum){
            throw new ArrayIndexOutOfBoundsException();
        }
        //------------  初始化 start  ----------------
        Integer numOfVexs = graph.vertexNum;
        List<Integer> unVisited=new ArrayList(); //未求出最短路径的点集合
        List<Integer> visited=new ArrayList(); //已求出最短路径的点集合
        HashMap path = new HashMap();  //路径

        double[] distance = new double[numOfVexs];// 存放源点到其他点的距离
        for (int i = 0; i < numOfVexs; i++) {
            distance[i] = Double.MAX_VALUE;
            unVisited.add(i);
        }
        Vertex[] verticeArr = graph.vertexList;
        for(Vertex temV : verticeArr){
            path.put(temV.getId(),startId.toString()); // 起始点 到 i点的路径
        }

        int startIndex = getVertexIndex(graph.vertexList,startId);//起始点在graph 数组里的下标
        distance[startIndex] = 0;
        visited.add(startIndex);
        unVisited.remove(startIndex);

        Edge current  = getVertex(graph.vertexList,startId).firstedge;
        while (current != null) {
            int temI = getVertexIndex(graph.vertexList,current.getEndPointId());
            distance[temI] = current.getDistance();
            path.put(current.getEndPointId(),path.get(current.getEndPointId())+"-->" + current.getEndPointId().toString());
            current = current.getNext();
        }

        while(!unVisited.isEmpty()){
            //求出距离x最短的点
            int minIndex=-1;
            Double min=Double.MAX_VALUE;
            for(int i=0;i<distance.length;i++){
                if(unVisited.contains(i)){
                    if(distance[i]<min){
                        minIndex=i;
                        min=distance[i];
                    }
                }
            }
            if(minIndex==-1){
                break;
            }
            //将该点加入到visited集合中，并从未访问集合中去掉
            visited.add(minIndex);
            unVisited.remove((Integer) minIndex);
            //对该点（u）为起点,相邻的点为终点的临接点进行松弛操作，更新其他点的当前最短路径
            for(Edge edge=graph.vertexList[minIndex].firstedge; edge!=null; edge=edge.getNext()){
                int v=edge.getEndPointId();
                //看是否满足distances[v]>distances[u]+w[u][v]
                Double value=edge.getDistance();
                int temV = getVertexIndex(graph.vertexList,v);
                if(distance[temV]>distance[minIndex]+value){
                    distance[temV]=distance[minIndex]+value;
                    path.put(v,path.get(edge.getPointId())+"-->" + v);

                }
            }
        }

        StringBuilder sb = new StringBuilder("到各点的最短距离:");

        Vertex[] vertices = graph.vertexList;
        log.info("到各点的最短距离:");
        for(int i=0;i< vertices.length;i++){
            log.info(startId +"到"+vertices[i].getId()+"点的距离为：" + distance[i]
                    + ";路径为："+ path.get(vertices[i].getId()).toString());
            sb.append(startId +"到"+vertices[i].getId()+"点的距离为：" + distance[i]
                    + ";路径为："+ path.get(vertices[i].getId()).toString());
        }
        return sb.toString();
    }

    @Override
    public Integer getNearestVertexId(Vertex record) {
        Integer result = 0;
        Map<String,Object> map = new HashMap<>();
        if(record == null){
            return 0;
        }
        if(record.getTunnelId() != null && record.getTunnelId() > 0){
            map.put("tunnelId",record.getTunnelId());
        }
        if(record.getStoreId() != null && record.getStoreId() > 0){
            map.put("storeId",record.getStoreId());
        }
        if(record.getAreaId() != null && record.getAreaId() > 0){
            map.put("areaId",record.getAreaId());
        }
        if(record.getLongitude() != null){
            map.put("longitude",record.getLongitude());
        }
        if(record.getLatitude() != null){
            map.put("latitude",record.getLatitude());
        }

        List<Vertex> vertexList = vertexMapper.listByMap(map);
        Map<Integer, Double> distanceMap = new HashMap<>();
        if(vertexList != null && vertexList.size() > 0){
            double long1 = record.getLongitude().doubleValue();
            double lat1 = record.getLatitude().doubleValue();
            for(Vertex tem : vertexList){
                double long2 = tem.getLongitude().doubleValue();
                double lat2 = tem.getLatitude().doubleValue();
                double distiance = GPSUtil.getDistance(long1,lat1,long2,lat2);
                distanceMap.put(tem.getId(),distiance);
            }

            Double min = Double.MAX_VALUE;
            for(Map.Entry<Integer,Double> temEntry : distanceMap.entrySet()){
                if(min > temEntry.getValue()){
                    min = temEntry.getValue();
                    result = temEntry.getKey();
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> listMapTest(Integer state) {
        List<Map<String, Object>> result = edgeMapper.listMapTest(state);

        return result;
    }


    public Graph creatGraph(){
        log.info("----   初始化Graph    -----");
        Graph graph = new Graph();
        Map<String,Object> map = new HashMap<String,Object>();
        List<Vertex> vertexList = vertexMapper.listByMap(map);
        List<Edge> edgeList = edgeMapper.listByMap(map);
        Integer vertexNum = vertexList.size();
        Integer edgeNum = edgeList.size();
        log.info("----  vertexNum =" + vertexNum +"   ----edgeNum=" + edgeNum);

        graph.setVertexNum(vertexNum); //顶点数
        graph.setEdgeNum(edgeNum); //边数

        //初始化顶点
        graph.vertexList=new Vertex[vertexNum];
        for(int i=0;i < vertexNum;i++){
            graph.vertexList[i]=vertexList.get(i);
            graph.vertexList[i].firstedge=null;
        }
        //初始化边
        for(Edge temEdge : edgeList){
            //获取边的起始顶点和结束顶点
            int p1=temEdge.getPointId();//起始点
            int p2=temEdge.getEndPointId(); //结束点
            Double weight=temEdge.getDistance(); //权重
            System.out.println(p1+"-"+p2);
            //生成边节点
            //Edge e1=new Edge();
            //.setEndPointId(p2);
            //e1.setDistance(weight);
            //把边节点添加到起始顶点的链表中
            Vertex tem = getVertex(graph.vertexList,p1);
            if(tem.firstedge==null){
                tem.firstedge = temEdge;
            }else{
                linkLast(tem.firstedge,temEdge);
            }
            //同样把边节点添加到结束顶点的链表中
//            Edge e2=new Edge();
//            e2.setEndPointId(p1);
//            e2.setDistance(weight);
//            if(graph.vertexList[p2].firstedge!=null){
//                linkLast(graph.vertexList[p2].firstedge,e2);
//            }else{
//                graph.vertexList[p2].firstedge=e2;
//            }
        }

        printGraph(graph);

        return graph;
    }

    public Vertex getVertex(Vertex[] vertexList,int startId){
        Vertex tem = new Vertex();
        for(int i=0;i<vertexList.length;i++){
            if(vertexList[i].getId() == startId){
                tem = vertexList[i];
            }
        }
        return tem;
    }

    public Integer getVertexIndex(Vertex[] vertexList,int vertexId){
        Integer tem = -1;
        for(int i=0;i<vertexList.length;i++){
            if(vertexList[i].getId() == vertexId){
                tem = i;
            }
        }
        return tem;
    }

    public void printGraph(Graph graph) {
        log.info("-----  打印 Graph  -----");
        Vertex[] vertexList = graph.vertexList;
        for (int i = 0; i < vertexList.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("顶点=" + vertexList[i].getPoint()+"---链接表=");

            Edge node = vertexList[i].firstedge;
            while (node != null) {
                sb.append(",{指向点="+ node.getEndPointId()+"-权重="+node.getDistance()+"}");
                node = node.getNext();
            }
            log.info(sb.toString());
        }
    }

    /**
     * 把边节点链接在最后
     * @param beg   顶点节点指向的第一个边节点
     * @param end   要加入的边节点
     */
    public void linkLast(Edge beg,Edge end){
        Edge tem = beg;
        while(tem.getNext() != null){
            tem = tem.getNext();
        }
        tem.setNext(end);
    }




//    public Graph creatGraph(ArrayList<Vertex> vexs, int[][] edges){
//        //初始化顶点数和边数
//        vertexNum=vexs.size();
//        edgeNum=edges.length;
//
//        //初始化顶点
//        adjList=new Vertex[vertexNum];
//        for(int i=0;i<adjList.length;i++){
//            adjList[i]=vexs.get(i);
//            adjList[i].firstedge=null;
//            System.out.println(adjList[i].getPoint());
//        }
//        //初始化边
//        for(int i=0;i<edgeNum;i++){
//            //获取边的起始顶点和结束顶点
//            int p1=edges[i][0];
//            int p2=edges[i][1];
//            Double weight=(double)edges[i][2];
//            System.out.println(p1+"-"+p2);
//            //生成边节点
//            Edge e1=new Edge();
//            e1.setEndPointId(p2);
//            e1.setDistance(weight);
//            //把边节点添加到起始顶点的链表中
//            if(adjList[p1].firstedge==null){
//                adjList[p1].firstedge=e1;
//            }else{
//                linkLast(adjList[p1].firstedge,e1);
//            }
//            //同样把边节点添加到结束顶点的链表中
//            Edge e2=new Edge();
//            e2.setEndPointId(p1);
//            e2.setDistance(weight);
//            if(adjList[p2].firstedge!=null){
//                linkLast(adjList[p2].firstedge,e2);
//            }else{
//                adjList[p2].firstedge=e2;
//            }
//        }
//    }

//
//    public int[] dijkstra(int v) {
//        if (v < 0 || v >= numOfVexs)
//            throw new ArrayIndexOutOfBoundsException();
//        boolean[] st = new boolean[numOfVexs];// 默认初始为false
//        int[] distance = new int[numOfVexs];// 存放源点到其他点的距离
//        for (int i = 0; i < numOfVexs; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
//        ENode current;
//        current = vexs[v].firstadj;
//        while (current != null) {
//            distance[current.adjvex] = current.weight;
//            current = current.nextadj;
//        }
//        distance[v] = 0;
//        st[v] = true;
//        // 处理从源点到其余顶点的最短路径
//        for (int i = 0; i < numOfVexs; i++) {
//            int min = Integer.MAX_VALUE;
//            int index = -1;
//            // 比较从源点到其余顶点的路径长度
//            for (int j = 0; j < numOfVexs; j++) {
//                // 从源点到j顶点的最短路径还没有找到
//                if (st[j] == false) {
//                    // 从源点到j顶点的路径长度最小
//                    if (distance[j] < min) {
//                        index = j;
//                        min = distance[j];
//                    }
//                }
//            }
//            // 找到源点到索引为index顶点的最短路径长度
//            if (index != -1)
//                st[index] = true;
//            // 更新当前最短路径及距离
//            for (int w = 0; w < numOfVexs; w++)
//                if (st[w] == false) {
//                    current = vexs[w].firstadj;
//                    while (current != null) {
//                        if (current.adjvex == index)
//                            if ((min + current.weight) < distance[w]) {
//                                distance[w] = min + current.weight;
//                                break;
//                            }
//                        current = current.nextadj;
//                    }
//                }
//        }
//        return distance;
//    }

}
