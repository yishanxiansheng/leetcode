package com.example.testmaven;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 字符串操作
 *
 * @author heshufan
 * @date 2020-12-28
 */
public class TestString2 {

    public static void main(String[] args) {
//        System.out.println(longestNiceSubstring("dDzeE"));
        int[] a = new int[]{0, 1};
        int[] b = new int[]{0, 0};
        int[][] c = new int[2][2];
        c[0] = a;
        c[1] = b;
        highestPeak(c);
    }


    public static int longestPalindrome(String s) {
        int result = 0;
        Map<Character, Integer> hashmap = new HashMap<>();
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            hashmap.put(ss[i], hashmap.getOrDefault(ss[i], 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : hashmap.entrySet()) {
            result += entry.getValue() / 2 * 2;
            if (entry.getValue() % 2 == 1 && result % 2 == 0) {
                result++;
            }
        }
        return result;
    }


    public static int[][] highestPeak(int[][] isWater) {
        int[][] res = new int[isWater.length][isWater[0].length];
        boolean[][] ii = new boolean[isWater.length][isWater[0].length];
        int num = 0;

        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                if (isWater[i][j] == 1) {
                    num++;
                    res[i][j] = 0;
                    ii[i][j] = true;
//                    if (i - 1 >= 0 && !ii[i - 1][j]) {
//                        res[i - 1][j] = 1;
//                        ii[i - 1][j] = true;
//                        num++;
//
//                    }
                    if (j - 1 >= 0 && !ii[i][j - 1]) {
                        res[i][j - 1] = 1;
                        ii[i][j - 1] = true;
                        num++;

                    }
                    if (i + 1 < res.length && !ii[i + 1][j]) {
                        res[i + 1][j] = 1;
                        ii[i + 1][j] = true;
                        num++;

                    }
                    if (j + 1 < res[0].length && !ii[i][j + 1]) {
                        res[i][j + 1] = 1;
                        ii[i][j + 1] = true;
                        num++;
                    }
                }
            }
        }

        int index = 1;
        while (num < isWater.length * isWater[0].length) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[0].length; j++) {
                    if (res[i][j] == index) {
                        if (i - 1 >= 0 && !ii[i - 1][j]) {
                            res[i - 1][j] = index + 1;
                            ii[i - 1][j] = true;
                            num++;

                        }
                        if (j - 1 >= 0 && !ii[i][j - 1]) {
                            res[i][j - 1] = index + 1;
                            ii[i][j - 1] = true;
                            num++;

                        }
                        if (i + 1 < res.length && !ii[i + 1][j]) {
                            res[i + 1][j] = index + 1;
                            ii[i + 1][j] = true;
                            num++;

                        }
                        if (j + 1 < res[0].length && !ii[i][j + 1]) {
                            res[i][j + 1] = index + 1;
                            ii[i][j + 1] = true;
                            num++;
                        }
                    }
                }
            }
            index++;
        }
        return res;
    }


    public static boolean canChoose(int[][] groups, int[] nums) {
        int start = 0;
        for (int i = 0; i < groups.length; i++) {
            int[] temp = groups[i];
            int length = temp.length;
            if (start + length > nums.length) {
                return false;
            }
            //找到与第i个数组相同的序列
            for (int j = start; j <= nums.length; j++) {
                if (j + length > nums.length) {
                    return false;
                }
                boolean a = true;
                for (int k = j; k < j + length; k++) {
                    if (nums[k] != temp[k - j]) {
                        a = false;
                        break;
                    }
                }
                if (a) {
                    start = j + length;
                    break;
                }
            }

        }
        return true;
    }

    public static String longestNiceSubstring(String s) {
        int max = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (longestNiceSubstring2(s.substring(i, j))) {
                    if (j - i + 1 > max) {
                        res = s.substring(i, j);
                        max = j - i + 1;
                    }
                }
            }
        }
        return res;
    }

    public static boolean longestNiceSubstring2(String s) {
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a >= 'a' && a <= 'z') {
                if (s.contains((a + "").toUpperCase())) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if (s.contains((a + "").toLowerCase())) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        return true;
    }

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int oldNum = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if ((entry.getValue() & 1) != 0) {
                oldNum++;
                if (oldNum > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
