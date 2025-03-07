package com.huahuadan.datastructure.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * <h3>广度优先搜索 Breadth-first search</h3>
 */
public class BFS {

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

        bfs(v1);
    }

    private static void bfs(Vertex v) {
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.offer(v);
        v.visited = true; // 入队时立即标记为已访问

        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            System.out.println(poll.name);
            for (Edge edge : poll.edges) {
                if (!edge.linked.visited) {
                    edge.linked.visited = true; // 防止重复访问，如果不是入队时就标记已经访问，而是出队时标记已访问，后出队的元素很有可能被多次访问，例如在同一层级的三个顶点指向了同一个顶点。
                    queue.offer(edge.linked);
                }
            }
        }
    }

}
