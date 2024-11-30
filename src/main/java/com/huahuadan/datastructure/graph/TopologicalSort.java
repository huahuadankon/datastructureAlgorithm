package com.huahuadan.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/30 19:41
 * @description 拓扑排序
 */
public class TopologicalSort {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = List.of(new Edge(v3)); // +1
        v2.edges = List.of(new Edge(v3)); // +1
        v3.edges = List.of(new Edge(v4));
        v6.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        v7.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);
        //统计顶点的入度
        for (Vertex v : graph) {
            for (Edge e : v.edges) {
                e.linked.inDegree++;
            }
        }

        //将入度为零的顶点添加到队列
        LinkedList<Vertex> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        for(Vertex v : graph){
            if(v.inDegree == 0){
                queue.offer(v);
            }
        }

        //依次出队，以及将当前顶点的相邻顶点的入度-1，若降为零，则将该顶点入队列
        while(!queue.isEmpty()){
            Vertex poll = queue.poll();
            result.add(poll.name);
            for (Edge e : poll.edges) {
                e.linked.inDegree--;
                if(e.linked.inDegree == 0){
                    queue.offer(e.linked);
                }
            }
        }

        if(result.size() != graph.size()){
            System.out.println("有环，出现错误");
        }else {
            for(String s : result){
                System.out.println(s);
            }
        }
    }
}
