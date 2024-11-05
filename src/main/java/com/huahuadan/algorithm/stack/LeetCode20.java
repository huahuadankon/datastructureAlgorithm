package com.huahuadan.algorithm.stack;

import com.huahuadan.datastructure.stack.ArrayStack;

/**
 * @author liuyichen
 * @version 1.0
 */
public class LeetCode20 {


    public static void main(String[] args) {
        LeetCode20 s = new LeetCode20();
        System.out.println(s.isValid("([{}])"));
        System.out.println(s.isValid("()[]{}"));
        System.out.println(s.isValid("()"));
        System.out.println("---------------------");

        System.out.println(s.isValid("[)"));
        // ]
        System.out.println(s.isValid("([)]"));
        // ) ]
        System.out.println(s.isValid("([]"));
        // )
        System.out.println(s.isValid("("));

        System.out.println("---------------------");
        System.out.println(s.isValid(")("));
        System.out.println(s.isValid("]"));
    }

    private boolean isValid(String s) {
        ArrayStack<Character> characters = new ArrayStack<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                characters.push(')');
            }else if(c == '['){
                characters.push(']');
            }else if(c == '{'){
                characters.push('}');
            }else if(!characters.isEmpty()&&characters.peek()==c){
                characters.pop();
            }else {
                return false;
            }
        }
        if(characters.isEmpty()){
            return true;
        }
        return false;
    }
}
