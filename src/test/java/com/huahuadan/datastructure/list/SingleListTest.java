package com.huahuadan.datastructure.list;

import org.junit.jupiter.api.Test;

/**
 * @author liuyichen
 * @version 1.0
 */
public class SingleListTest {

    @Test
    public void test1() {
        SingleList singleList = new SingleList();
        singleList.addFirst(1);
        singleList.addFirst(2);
        singleList.addFirst(3);
        singleList.addFirst(4);
        System.out.println("遍历一");
        singleList.loop();
        System.out.println("----遍历二----");
        singleList.loop2((x)-> System.out.println(x));
//        singleList.forEach(System.out::println);
        System.out.println("----遍历3----");
       /* Iterator<Integer> iterator = singleList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
        for (Integer i : singleList){
            System.out.println(i);
        }


    }
    @Test
    public void test2() {
        SingleList singleList = new SingleList();
        singleList.addLast(1);
        singleList.addLast(2);
        singleList.addLast(3);
        singleList.addLast(4);
        singleList.loop();
    }

    @Test
    public void test3() {
        SingleList singleList = new SingleList();
        singleList.addLast(1);
        singleList.addLast(2);
        singleList.addLast(3);
        singleList.addLast(4);
        int i = singleList.get(3);
        singleList.insert(0,5);
        singleList.loop();
    }

    @Test
    public void test4() {
        SingleList singleList = new SingleList();
        singleList.addLast(1);
        singleList.addLast(2);
        singleList.addLast(3);
        singleList.addLast(4);
        singleList.removeFirst();
        singleList.loop();
    }

    @Test
    public void test5() {
        SingleList singleList = new SingleList();
        singleList.addLast(1);
        singleList.addLast(2);
        singleList.addLast(3);
        singleList.addLast(4);
        singleList.remove(3);
        singleList.loop();
    }




}
