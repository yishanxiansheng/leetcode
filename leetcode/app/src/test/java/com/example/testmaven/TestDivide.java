package com.example.testmaven;

/**
 * @author heshufan
 * @date 2021/2/27
 */

public class TestDivide {

    public static void main(String[] agrs){
        System.out.println(longestSubstring("weitong",2));
    }

    /**
     * 至少有K个重复字符的最长子串
     *
     * @param s
     * @param k
     * @return
     */
    static int max = 0;

    public static int longestSubstring(String s, int k) {
        if(k==1&&s.length() == 1){
            return 1;
        }
        dfs(s, 0, s.length() - 1, k);
        return max;
    }

    private static void dfs(String s, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int[] num = new int[26];
        for (int i = left; i <= right; i++) {
            char a = s.charAt(i);
            num[a - 'a']++;
        }
        int split = 0;
        boolean a = false;
        for (int i = left; i <= right; i++) {
            int d = s.charAt(i) - 'a';
            if (num[d] > 0 && num[d] < k) {
                split = i;
                a = true;
                break;
            }
        }
        if (split == 0 && !a) {
            max = Math.max(max, right - left + 1);
            return;
        }
        dfs(s, left, split-1, k);
        dfs(s, split + 1, right, k);
    }
}
