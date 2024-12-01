package com.huahuadan.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/30 20:27
 * @description 迪杰斯特拉算法求最短路径,不能计算带有负权的边，本质上还是贪心思想的问题，带有负权的边无法局部最优推全局最优
 */
public class Dijkstra {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);
        dijkstra(graph,v1);
    }

    /**
     *
     * @param graph
     * @param source 起始顶点
     */
    private static void dijkstra(List<Vertex> graph, Vertex source) {
        List<Vertex> list = new ArrayList<>(graph);
        source.dist = 0;
        while (!list.isEmpty()) {
            //找到距离其实顶点距离最短的顶点作为当前顶点,此时不可能通过其他顶点来更新这个最小值(在所有边权值都不为负数的情况下)
            Vertex curr = chooseMinDistVertex(list);
            //更新当前顶点邻居顶点的距离
            updateNeighboursDist(curr);
            //移除当前顶点
            list.remove(curr);
            curr.visited = true;
        }
        for (Vertex v : graph) {
            System.out.println(v);
        }

    }

    private static void updateNeighboursDist(Vertex curr) {
        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            if (!n.visited) {
                int dist = curr.dist + edge.weight;
                if (dist < n.dist) {
                    n.dist = dist;
                    n.prev = curr;
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(List<Vertex> list) {
        Vertex min = list.get(0);
        for (Vertex v : list) {
            if (v.dist < min.dist) {
                min = v;
            }
        }
        return min;
    }

}
