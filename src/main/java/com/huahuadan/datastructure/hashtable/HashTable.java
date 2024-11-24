package com.huahuadan.datastructure.hashtable;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/24 13:12
 * @description 哈希表
 */

import com.google.common.collect.Table;
import com.google.common.hash.Hashing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h2>哈希表</h2>
 * <p>给每份数据分配一个编号，放入表格（数组）。</p>
 * <p>建立编号与表格索引的关系，将来就可以通过编号快速查找数据</p>
 * <ol>
 *  <li>理想情况编号当唯一，数组能容纳所有数据</li>
 *  <li>现实是不能说为了容纳所有数据造一个超大数组，编号也有可能重复</li>
 * </ol>
 * <p>
 * 解决
 * <ol>
 *     <li>有限长度的数组，以【拉链】方式存储数据</li>
 *     <li>允许编号适当重复，通过数据自身来进行区分</li>
 * </ol>
 */
public class HashTable {
    // 节点类
    static class Entry {
        int hash; // 哈希码
        Object key; // 键
        Object value; // 值
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0; // 元素个数
    float loadFactor = 0.75f; // 12 阈值
    int threshold = (int) (loadFactor * table.length);

    /* 求模运算替换为位运算
        - 前提：数组长度是 2 的 n 次方
        - hash % 数组长度 等价于 hash & (数组长度-1)
     */
    // 根据 hash 码获取 value
    Object get(int hash, Object key) {
        //通过hash值映射索引
        int index = hash & (table.length - 1);
        if(table[index] == null){
            return null;
        }
        Entry p = table[index];
        while(p != null){
            if(p.key.equals(key)){
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    // 向 hash 表存入新 key value，如果 key 重复，则更新 value
    void put(int hash, Object key, Object value) {
        int index = hash & (table.length - 1);
        // 检查链表头节点的 key 是否匹配
        if (table[index] == null) {
            table[index] = new Entry(hash, key, value);
        } else {
            Entry p = table[index];
            // 首节点检查
            if (p.key.equals(key)) {
                p.value = value;
                return;
            }
            while (p.next != null) {
                if (p.next.key.equals(key)) {
                    p.next.value = value;
                    return;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value);
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }
    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            if (p != null) {
                /*
                拆分链表，移动到新数组，拆分规律
                * 一个链表最多拆成两个
                * hash & table.length == 0 的一组
                * hash & table.length != 0 的一组
                                          p
                0->8->16->24->32->40->48->null
                            a
                0->16->32->48->null
                        b
                8->24->40->null
             */
                Entry a = null;
                Entry b = null;
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if((p.hash & table.length) == 0){
                        if(a !=null){
                            a.next = p;//直接操作了原始链表中a指向节点的next指向，其实原始链表已经被实际意义上分割了。
                        }else {
                            aHead = p; //始终指向的是a链表的第一个节点
                        }
                        a = p;
                    }else {
                        if(b !=null){
                            b.next = p;
                        }else {
                            bHead = p;
                        }
                        b = p;
                    }
                    p = p.next;
                }
                if(a!=null){
                    a.next = null; //避免循环指向
                    newTable[i] = aHead;
                }
                if(b!=null){
                    b.next = null;
                    newTable[i+table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    // 根据 hash 码删除，返回删除的 value
    Object remove(int hash, Object key) {
        int index = hash & (table.length - 1);
        if(table[index] == null){
            return null;
        }
        Entry p = table[index];
        Entry prev = null;
        while(p != null){
            if(p.key.equals(key)){
                if(prev == null){ // 链表头
                    table[index] = p.next;
                }else {
                    prev.next = p.next;

                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    public Object get(Object key) {
        int hash = hash(key);
        return get(hash, key);
    }

    public void put(Object key, Object value) {
        int hash = hash(key);
        put(hash, key, value);
    }

    public Object remove(Object key) {
        int hash = hash(key);
        return remove(hash, key);
    }

    private static int hash(Object key) {
        if (key instanceof String k) {
            return Hashing.murmur3_32().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    public void print() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p != null) {
                sums[i]++;
                p = p.next;
            }
        }
//        System.out.println(Arrays.toString(sums));

        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(collect);
    }

    public static void main(String[] args) throws IOException {
        HashTable table = new HashTable();
        /*for (int i = 0; i < 200000; i++) {
            Object obj = new Object();
            table.put(obj, obj);
        }*/
        /*List<String> strings = Files.readAllLines(Path.of("words"));
        for (String string : strings) {
            table.put(string, string);
        }*/

        table.put(2, 2);
        table.put(524290, 2);

        table.print();
    }

    /*
    为什么计算索引位置用式子：
        【hash & (数组长度-1)】 等价于 【hash % 数组长度】
        10进制中去除以 10，100，1000时，余数就是被除数的后1，2，3 位
                    10^1 10^2 10^3
        2进制中去除以 10，100，1000时，余数也是被除数的后1，2，3 位
                    2^1 2^2 2^3 2^4
        因此求余数就是求二进制的后几位，而保留二进制后几位可以通过与
            1，3，7，11 ... 等数字按位与来实现，这些数字恰巧是数组长度-1

    为什么旧链表会拆分成两条，一条 hash & 旧数组长度==0 另一条!=0
        旧数组长度换算成二进制后，其中的 1 就是我们要检查的倒数第几位
            旧数组长度 8 二进制 => 1000 检查倒数第4位
            旧数组长度 16 二进制 => 10000 检查倒数第5位
        hash & 旧数组长度 就是用来检查扩容前后索引位置（余数）会不会变
    为什么拆分后的两条链表，一个原索引不变，另一个是原索引+旧数组长度
    它们都有个共同的前提：数组长度是 2 的 n 次方

 */


}
