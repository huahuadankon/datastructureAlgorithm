package com.huahuadan.datastructure.graph;

import com.huahuadan.datastructure.stack.LinkedListStack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/30 19:20
 * @description 深度优先遍历
 */
public class DFS {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(
                new Edge(v3, 9),
                new Edge(v2, 7),
                new Edge(v6, 14)
        );
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(
                new Edge(v4, 11),
                new Edge(v6, 2)
        );
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        dfs2(v1);
    }

    //递归
    private static void dfs(Vertex v) {
        v.visited = true;
        System.out.println(v.name);
        for (Edge e : v.edges) {
            if(!e.linked.visited){
                dfs(e.linked);
            }
        }
    }
    //非递归
    private static void dfs2(Vertex v) {
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            Vertex top = stack.pop();
            top.visited = true;
            System.out.println(top.name);
            for (Edge e : top.edges) {
                if(!e.linked.visited){
                    stack.push(e.linked);
                }
            }
        }
    }
}
