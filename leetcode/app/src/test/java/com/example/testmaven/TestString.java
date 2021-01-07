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
        System.out.println("a".substring(0, 0));

        System.out.println(longestPalindrome("babad") + "M");

    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        //data[i][j]指s中i到j的子串是否是回文串,是为1 0位否
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
        int n1 = num1.length()-1;
        int n2 = num2.length()-1;
        if(n1 < 0 || n2 < 0) return "";
        int[] mul = new int[n1+n2+2];

        for(int i = n1; i >= 0; --i) {
            for(int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                bitmul += mul[i+j+1]; // 先加低位判断是否有新的进位

                mul[i+j] += bitmul / 10;
                mul[i+j+1] = bitmul % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while(i < mul.length-1 && mul[i] == 0)
            i++;
        for(; i < mul.length; ++i)
            sb.append(mul[i]);
        return sb.toString();
    }
}