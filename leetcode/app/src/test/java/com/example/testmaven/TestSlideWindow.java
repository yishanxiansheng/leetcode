package com.example.testmaven;

import android.util.ArraySet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author heshufan
 * @date 2021/2/21
 */

public class TestSlideWindow {
    public static void main(String[] args) {
        permutation("abc");
    }


    public static String[] permutation(String s) {
        int length = s.length();
        if (length == 1) {
            return new String[]{s};
        }
        int deep = 0;
        Set<String> res = new HashSet<>();
        boolean[] choosed = new boolean[length];
        StringBuffer buffer = new StringBuffer();
        permutationHelper(s, deep, res, choosed, buffer);
        return res.toArray(new String[res.size()]);
    }

    private static void permutationHelper(String s, int deep, Set<String> res, boolean[] choosed, StringBuffer buffer) {
        if (deep == s.length()) {
            res.add(buffer.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!choosed[i]) {
                choosed[i] = true;
                buffer.append(s.charAt(i));
                permutationHelper(s, deep + 1, res, choosed, buffer);
                choosed[i] = false;
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
    }


    /**
     * 绝对差不超过限制的最长连续子数组
     * 滑动窗口加有序结合
     * 有序集合是为了方便计算这个窗口中的最大差值
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        //红黑树  key会去重  value为key出现的次数，是为了防止窗口中有重复的数据，所以不能直接删除
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < nums.length) {
            treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            if (treeMap.lastKey() - treeMap.firstKey() > limit) {
                treeMap.put(nums[left], treeMap.get(nums[left]) - 1);
                if (treeMap.get(nums[left]) == 0) {
                    treeMap.remove(nums[left]);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0) {
                        res++;
                    }
                    if (i == grid.length - 1) {
                        res++;
                    }
                    if (j == 0) {
                        res++;
                    }
                    if (j == grid[0].length - 1) {
                        res++;
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 0) {
                        res++;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 0) {
                        res++;
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 0) {
                        res++;
                    }
                    if (i + 1 < grid.length && grid[i + 1][j] == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 1052. 爱生气的书店老板
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if (customers == null || customers.length == 0) {
            return 0;
        }
        int max = 0;
        int total = 0;

        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] != 1) {
                total += customers[i];
            }
        }
        int res = total;
        for (int i = 0; i < customers.length; i++) {
            if (i < X && grumpy[i] == 1) {
                res += customers[i];
            }
            if (i >= X) {
                if (grumpy[i - X] == 1) {
                    res -= customers[i - X];
                }
                if (grumpy[i] == 1) {
                    res += customers[i];
                }
            }
            max = Math.max(max, res);
        }
        return max;
    }
}
