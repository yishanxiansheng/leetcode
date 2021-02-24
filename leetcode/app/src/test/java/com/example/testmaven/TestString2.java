package com.example.testmaven;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
 * 字符串操作
 *
 * @author heshufan
 * @date 2020-12-28
 */
public class TestString2 {

    public static void main(String[] args) {
//        System.out.println(longestNiceSubstring("dDzeE"));
        int[] a = new int[]{0,1};
        int[] b = new int[]{0,0};
        int[][] c = new int[2][2];
        c[0] = a;
        c[1] = b;
//        System.out.println(canChoose(c, new int[]{4978581, -4363542, 9212539, -6038177, 9212539, -5913070, 8296798, -9556545, 2540206, -468950, 4091738, -2988962, 1977436, 6468826, -5913070, -5416666, -9189639, 8299815, -1581790, 2859950, 6789680, -3127323, -7477258, 9102825, -6371451, 9144300}));
        highestPeak(c);
    }


    public static int[][] highestPeak(int[][] isWater) {
        int[][] res = new int[isWater.length][isWater[0].length];
        boolean[][] ii = new boolean[isWater.length][isWater[0].length];
        int num = 0;

        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                if (isWater[i][j] == 1) {
                    num++;
                    res[i][j]=0;
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
                            res[i - 1][j] = index+1;
                            ii[i - 1][j] = true;
                            num++;

                        }
                        if (j - 1 >= 0 && !ii[i][j - 1]) {
                            res[i][j - 1] = index+1;
                            ii[i][j - 1] = true;
                            num++;

                        }
                        if (i + 1 < res.length && !ii[i + 1][j]) {
                            res[i + 1][j] = index+1;
                            ii[i + 1][j] = true;
                            num++;

                        }
                        if (j + 1 < res[0].length && !ii[i][j + 1]) {
                            res[i][j + 1] = index+1;
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
        return true;
    }
}
