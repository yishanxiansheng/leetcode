package com.example.testmaven;

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
}
