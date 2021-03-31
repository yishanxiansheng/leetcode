package com.example.testmaven;

import android.util.ArraySet;

import com.example.testmaven.designmode.Main;

import java.io.IOException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 字符串操作
 *
 * @author heshufan
 * @date 2020-12-28
 */
public class TestString {

    public static void main(String[] args) {
        System.out.println(reverseString1("heshufan"));
        System.out.println(reverseString2("heshufan"));
        System.out.println(reverseString3("heshufan"));
        System.out.println(reverseString4("heshufan"));

        System.out.println(findLongestSubString("heshufan", "shu"));
        System.out.println("a".substring(0, 0));

        System.out.println(longestPalindrome("babad") + "M");

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));


        //字符参与运行的运算的时候是ascii码值
        System.out.println(1 + 'c');
        //数字作为ascii码值转为字符
        System.out.println((char) 99);

        isAnagram("nl", "cx");

        String str = "haha";
        new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }.start();
        equalSubstring("krrgw", "zjxss", 19);
        removeOuterParentheses("(()())(())");
        minWindow("bbaa", "aba");
    }

    /**
     * 最小覆盖子串
     * 双指针法
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s.equals(t)) {
            return t;
        }
        int res_left = 0, red_right = 0;
        int left = 0;
        int right = t.length() - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            if (contains(s.substring(left, right + 1), t)) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    res_left = left;
                    red_right = right;
                }
                left++;
            } else {
                right++;
            }

        }
        return red_right == res_left ? s.substring(res_left, red_right + 1) : "";
    }

    public static boolean contains(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (!s.contains(t.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除最外层的括号
     *
     * @param S
     * @return
     */
    public static String removeOuterParentheses(String S) {
        StringBuffer res = new StringBuffer();
        Deque<Character> deque = new ArrayDeque<>();
        int left = 0;
        for (int i = 0; i < S.length(); i++) {
            if ('(' == S.charAt(i)) {
                deque.push(S.charAt(i));
            } else {
                if ('(' == deque.peek()) {
                    deque.pop();
                    if (deque.size() == 0) {
                        String ss = S.substring(left, i + 1);
                        if (ss.length() > 2) {
                            res.append(ss.substring(1, ss.length() - 1));
                        }
                        left = i + 1;
                    }
                }
            }
        }
        return res.toString();
    }

    /**
     * 尽可能使字符串相等
     * 转化为滑动窗口的最大值
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        //最为关键的一点
        int[] diff = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        int left = 0;
        int right = 0;
        int max = 0;
        int sum = 0;
        while (left <= right && right < s.length()) {
            sum += diff[right];
            if (sum <= maxCost) {
                max = Math.max(right - left + 1, max);
            } else {
                sum -= diff[left];
                left++;
            }
            right++;
        }
        return max;
    }

    /**
     * 最长回文子串
     * 转移方程
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        //data[i][j]指s中i到j的子串是否是回文串,是为true 不是为false
        boolean[][] data = new boolean[s.length()][s.length()];
        int a = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            data[i][i] = true;
        }

        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    data[i][j] = false;
                } else {
                    if (j - i < 3) {
                        data[i][j] = true;
                    } else {
                        data[i][j] = data[i + 1][j - 1];
                    }
                }
                if (data[i][j]) {
                    if (length < s.substring(i, j + 1).length()) {
                        a = i;
                        length = j + 1 - i;
                    }
                }
            }
        }
        return s.substring(a, a + length);
    }

    /**
     * 字符串反转
     *
     * @return
     */
    public static String reverseString1(String string) {
        String result = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            result = result + string.charAt(i);
        }
        return result;
    }

    /**
     * 字符串反转
     *
     * @return
     */
    public static String reverseString2(String string) {
        char[] result = string.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = result.length - 1; i >= 0; i--) {
            buffer.append(result[i]);
        }
        return buffer.toString();
    }

    /**
     * 字符串反转
     *
     * @return
     */
    public static String reverseString3(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    /**
     * 递归
     *
     * @return
     */
    public static String reverseString4(String string) {
        int length = string.length();
        if (string.length() == 1) {
            return string;
        }

        String left = string.substring(0, string.length() / 2);
        String right = string.substring(string.length() / 2);

        return reverseString4(right) + reverseString4(left);
    }

    /**
     * 最长公共子串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestSubString(String str1, String str2) {

        //data[i][j]代表str前i的子串与str2前j的子串的最长公共子串的长度
        int[][] data = new int[str1.length()][str2.length()];
        for (int i = 0; i < data.length; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                data[i][0] = 1;
            }
        }

        for (int i = 0; i < data[0].length; i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                data[0][i] = 1;
            }
        }

        int max = 0;
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    data[i][j] = data[i - 1][j - 1] == 0 ? 1 : data[i - 1][j - 1] + 1;
                    if (data[i][j] > max) {
                        max = data[i][j];
                    }
                }
            }
        }
        return max;
    }

    /**
     * 字符串相加
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int temp = 0;
        StringBuffer buffer = new StringBuffer();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                temp += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                temp += num2.charAt(j--) - '0';
            }
            buffer.append(temp % 10);
            temp = temp / 10;
        }
        return buffer.reverse().toString();
    }

    /**
     * 两个字符串相乘
     * 两个字符串相乘的结果的长度为m+n+1或者m+n
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        if (n1 < 0 || n2 < 0) return "";
        int[] mul = new int[n1 + n2 + 2];

        for (int i = n1; i >= 0; --i) {
            for (int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                bitmul += mul[i + j + 1]; // 先加低位判断是否有新的进位

                mul[i + j] += bitmul / 10;
                mul[i + j + 1] = bitmul % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while (i < mul.length - 1 && mul[i] == 0)
            i++;
        for (; i < mul.length; ++i)
            sb.append(mul[i]);
        return sb.toString();
    }

    /**
     * 判断是否是回文串
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        String s1 = s.toLowerCase().replace(" ", "");
        char[] chars = s1.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if ((chars[start] >= '0' && chars[start] <= '9') || (chars[start] >= 'a' && chars[start] <= 'z')) {
                if ((chars[end] >= '0' && chars[end] <= '9') || (chars[end] >= 'a' && chars[end] <= 'z')) {
                    if (chars[start] != chars[end]) {
                        return false;
                    }
                    start++;
                    end--;
                } else {
                    end--;
                }
            } else {
                start++;
            }
        }
        return true;
    }

    /**
     * 找不同
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.toCharArray().length; i++) {
            res = res ^ s.charAt(i);
        }

        for (int i = 0; i < t.toCharArray().length; i++) {
            res = res ^ t.charAt(i);
        }
        return (char) res;
    }

    /**
     * 字母异味词
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        int[] acountS = new int[26];
        int[] acountT = new int[26];

        int lengthS = s.toCharArray().length;
        int lengthT = t.toCharArray().length;
        if (lengthS != lengthT) {
            return false;
        }
        for (int i = 0; i < s.toCharArray().length; i++) {
            acountS[s.charAt(i) - 'a']++;
            acountT[t.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (acountS[i] != acountT[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 反转字符串中的单词 III
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] data = s.split(" ");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            result.append(reverseWords2(data[i]));
        }
        return result.toString();
    }

    public String reverseWords2(String s) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            result.append(s.charAt(s.length() - 1 - i));
        }
        return result.toString();
    }

    /**
     * 替换后的最长重复字符
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int left = 0;
        int[] acount = new int[26];
        int max = 0;
        //right作为滑动窗口的右边界
        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            acount[index]++;
            max = Math.max(acount[index], max);
            if (right - left + 1 - max > k) {
                acount[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return s.length() - left + 1;
    }

    /**
     * Excel表列序号
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            result += (chars[i] - 'A' + 1) * Math.pow(26, chars.length - 1 - i);
        }
        return result;
    }

    /**
     * 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return res;
        }
        int[] ss1 = new int[26];
        int[] ss2 = new int[26];

        for (int i = 0; i < p.length(); i++) {
            ss1[s.charAt(i) - 'a']++;
            ss2[p.charAt(i) - 'a']++;
        }
        if (matched2(ss1, ss2)) {
            res.add(0);
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            ss2[s.charAt(i + p.length()) - 'a']++;
            ss2[s.charAt(i) - 'a']--;
            if (matched2(ss1, ss2)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean matched2(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 分治法
     * 241、为运算表达式设计优先级
     *
     * @param input
     * @return
     */
    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char data = input.charAt(i);
            if (data == '*' || data == '+' || data == '-') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int j = 0; j < left.size(); j++) {
                    for (int k = 0; k < right.size(); k++) {
                        if (data == '*') {
                            res.add(left.get(j) * right.get(k));
                        } else if (data == '+') {
                            res.add(left.get(j) + right.get(k));
                        } else if (data == '-') {
                            res.add(left.get(j) - right.get(k));
                        }
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

    /**
     * s是否为t的子序列
     * 双指针
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int indexS = 0;
        int indexT = 0;

        while (indexS < s.length() && indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
            }
            indexT++;
        }
        return indexS == s.length();
    }

    public Set<Integer> set = new HashSet<>();

    public int[] findMode(TreeNode root) {

        findModeHelper(root);
        int[] res = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            res[i++] = iterator.next();
        }

        return res;
    }

    public void findModeHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null && root.val == root.left.val) {
            set.add(root.val);
        }

        if (root.right != null && root.val == root.right.val) {
            set.add(root.val);
        }
        findModeHelper(root.left);
        findModeHelper(root.right);
    }
}
