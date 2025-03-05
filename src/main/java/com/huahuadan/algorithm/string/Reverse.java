package com.huahuadan.algorithm.string;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/4 19:28
 * @description
 */
public class Reverse {
    //简单的反转字符串
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while(l < r){
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
    //有条件的反转字符串
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for(int i = 0;i < ch.length;i += 2 * k){
            int start = i;
            // 判断尾数够不够k个来取决end指针的位置
            int end = Math.min(ch.length - 1,start + k - 1);
            while(start < end){

                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;

                start++;
                end--;
            }
        }
        return new String(ch);
    }

    //反转字符串里面的单词
    public String reverseWords(String s) {
        // 1. 去除多余空格并规范化为标准格式
        StringBuilder sb = trimSpaces(s);
        // 2. 反转整个字符串
        reverse(sb, 0, sb.length() - 1);
        // 3. 反转每个单词以恢复单词原始顺序
        reverseEachWord(sb);
        return sb.toString();
    }

    // 去除多余空格（首尾空格 + 中间连续空格）
    private StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 跳过首部空格
        while (left <= right && s.charAt(left) == ' ') left++;
        // 跳过尾部空格
        while (left <= right && s.charAt(right) == ' ') right--;

        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else {
                // 仅当上一个字符不是空格时才添加空格
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
            }
            left++;
        }
        return sb;
    }
    //去除多余空格方法2
    public char[] removeExtraSpaces(char[] chars) {
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            //先用 fast 移除所有空格
            if (chars[fast] != ' ') {
                //在用 slow 加空格。 除第一个单词外，单词末尾要加空格
                if (slow != 0)
                    chars[slow++] = ' ';
                //fast 遇到空格或遍历到字符串末尾，就证明遍历完一个单词了
                while (fast < chars.length && chars[fast] != ' ')
                    chars[slow++] = chars[fast++];
            }
        }
        //相当于 c++ 里的 resize()
        char[] newChars = new char[slow];
        System.arraycopy(chars, 0, newChars, 0, slow);
        return newChars;
    }

    // 反转指定区间的字符
    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }

    // 逐个反转每个单词
    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 找到单词的结束位置
            while (end < n && sb.charAt(end) != ' ') end++;
            // 反转当前单词
            reverse(sb, start, end - 1);
            // 移动到下一个单词的起始位置
            start = end + 1;
            end = start;
        }
    }
}
