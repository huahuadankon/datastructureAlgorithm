package com.huahuadan.datastructure.graph;

import java.util.LinkedList;
import java.util.List;
/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/30 20:08
 * @description 深度优先遍历版本的拓扑排序
 */
public class TopologicalSortDFS {
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
        v7.edges = List.of(new Edge(v5));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);

        LinkedList<String> stack = new LinkedList<>();
        for(Vertex v : graph){
            dfs(v,stack);
        }
        System.out.println(stack);

    }
    private static void dfs(Vertex v, LinkedList<String> stack){
        if(v.status == 2){
            return;
        }
        if(v.status == 1){
            throw new RuntimeException("出现环");
        }
        v.status = 1;
        for(Edge e : v.edges){
            dfs(e.linked, stack);
        }
        v.status = 2;
        stack.push(v.name);
    }
}
