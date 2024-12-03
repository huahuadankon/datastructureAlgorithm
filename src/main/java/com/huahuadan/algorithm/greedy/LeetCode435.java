package com.huahuadan.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/3 21:12
 * @description 无重叠区间，本质上就是活动选择问题
 */
public class LeetCode435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按照区间的结束时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];  // 按照结束时间升序排序
            }
        });

        int pre = intervals[0][1];  // 记录上一个区间的结束时间
        int count = 1;  // 至少一个区间不需要删除
        for (int i = 1; i < intervals.length; i++) {
            int cur = intervals[i][0];  // 当前区间的起始时间
            if (pre <= cur) {  // 如果当前区间不重叠
                pre = intervals[i][1];  // 更新结束时间为当前区间的结束时间
                count++;  // 区间数增加
            }
        }

        // 返回总区间数减去不重叠区间的数目，得到需要删除的区间数
        return intervals.length - count;
    }


    public static void main(String[] args) {
        LeetCode435 leetCode435 = new LeetCode435();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(leetCode435.eraseOverlapIntervals(intervals));
    }
}
