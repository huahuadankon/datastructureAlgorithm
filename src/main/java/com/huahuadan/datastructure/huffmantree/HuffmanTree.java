package com.huahuadan.datastructure.huffmantree;

import java.util.*;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/3 19:58
 * @description 哈夫曼树
 */
public class HuffmanTree {
    static class Node {
        Character ch; // 字符
        int freq;     // 频次
        Node left;
        Node right;
        String code;  // 编码

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int freq() {
            return freq;
        }

        boolean isLeaf() {
            return left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    '}';
        }
    }
     /*
        Huffman 树的构建过程

        1. 将统计了出现频率的字符，放入优先级队列

        2. 每次出队两个频次最低的元素，给它俩找个爹
        3. 把爹重新放入队列，重复 2~3
        4. 当队列只剩一个元素时，Huffman 树构建完成
     */
    Map<Character, Node> map = new HashMap<>();
    Node root;
    String str;

    public HuffmanTree(String str) {
        this.str = str;
        //统计频率
        char[] ch = str.toCharArray();
        for (char c : ch) {
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }

        //将节点加入优先级队列
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::freq));
        queue.addAll(map.values());
        while(queue.size()>=2){
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            int freq = node1.freq + node2.freq;
            queue.offer(new Node(freq, node1, node2));
        }
        root = queue.poll();
        //计算每个字符的编码,以及编码后所占用的bit数
        int num = dfs(root,new StringBuilder());
        for (Node node : map.values()) {
            System.out.println(node+ " "+node.code);
        }
        System.out.println("总共占用的bit位 "+num);

    }
    private int dfs(Node node, StringBuilder code) {
        int num = 0;
        if(node.isLeaf()){
            node.code=code.toString();
            num = node.freq*code.length();
        }else {
           num += dfs(node.left,code.append('0'));
           num += dfs(node.right,code.append('1'));
        }
        if(!code.isEmpty()){
            code.deleteCharAt(code.length()-1);
        }
        return num;
    }
    //编码
    private String encode(){
        StringBuilder sb = new StringBuilder();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(map.get(c).code);
        }
        return sb.toString();
    }

    //解码
     /*
            从根节点，寻找数字对应的字符
                数字是 0 向左走
                数字是 1 向右走
                如果没走到头，每走一步数字的索引 i++
            走到头就可以找到解码字符，再将 node 重置为根节点

            a 00
            b 10
            c 1
                            i
            0   0   0   1   0   1   1   1   1   1   1   1   1
         */
    private String decode(String code){
        char[] ch = code.toCharArray();
        StringBuilder sb = new StringBuilder();
        Node node = root;
        int i = 0;
        while(i<ch.length){
            if(!node.isLeaf()){
                if(ch[i] == '0'){
                    node = node.left;
                } else if (ch[i] == '1') {
                    node = node.right;
                }
                i++;
            }
            if(node.isLeaf()){
                sb.append(node.ch);
                node = root;
            }

        }
        return sb.toString();

    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree("abbcccccdddba");
        System.out.println(huffmanTree.encode());
        System.out.println(huffmanTree.decode(huffmanTree.encode()));

    }




}
