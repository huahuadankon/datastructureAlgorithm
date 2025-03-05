package com.huahuadan.algorithm.stack;

import java.util.LinkedList;

/**
 * @author liuyichen
 * @version 1.0
 */
public class LeetCode150 {
    /*

        |   |
        | 13|
        | 4 |
        _____

        "4","13","5","/","+"

        - 遇到数字压入栈
        - 遇到运算符, 就从栈弹出两个数字做运算, 将结果压入栈
        - 遍历结束, 栈中剩下的数字就是结果
     */

    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens){
            switch (token){
                    case "+" ->{
                    stack.push(stack.pop() + stack.pop());
                }
                case "-" ->{
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(b-a);
                }
                case "*" ->{
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(b*a);
                }
                case "/" ->{
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(b/a);
                }
                default -> stack.push(Integer.parseInt(token));
            }

        }
        return stack.pop();
    }


    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));//22
    }
}
