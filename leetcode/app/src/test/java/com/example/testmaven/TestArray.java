package com.example.testmaven;

import android.util.ArraySet;

import androidx.annotation.IntRange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/13 11:21
 */
public class TestArray {

    public static void main(String[] args) {
        int[] array = new int[]{999999998, 999999997, 999999999};
//        minNumber(array);
        //将application/x-www-form-urlencoded字符串转换成普通字符串
        //采用UTF-8字符集进行解码
        try {
            System.out.println(URLDecoder.decode("%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6", "UTF-8"));
            //采用GBK字符集进行解码
            System.out.println(URLDecoder.decode("%B1%B1%BE%A9%B4%F3%D1%A7", "GBK"));

            // 将普通字符串转换成application/x-www-form-urlencoded字符串
            //采用utf-8字符集
            System.out.println(URLEncoder.encode("北京大学", "UTF-8"));
            //采用GBK字符集
            System.out.println(URLEncoder.encode("北京大学", "GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(getCommonDivide2(4, 6) + "");

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(removeDigits("1593212", 3));
        System.out.println(Arrays.toString(addBigNumber("426709752318", "95481253129")));


        System.out.println(Arrays.toString(merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3)));

    }

    /**
     * 0～n-1中缺失的数字
     * 二分法的标准写法
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        //这里带=号
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] != mid) {
                //这里减1
                right = mid - 1;
            } else {
                //这里加1
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //从右上角开始比较
        //行
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rows = matrix.length;
        //列
        int colums = matrix[0].length;

        int row = 0, colum = colums - 1;

        while (row < rows && colum >= 0) {
            if (matrix[row][colum] == target) {
                return true;
            } else if (matrix[row][colum] > target) {
                colum--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 构建乘积数组
     */
    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        int cur = 1;
        //先计算i左边的所有数相乘
        for (int i = 0; i < a.length; i++) {
            result[i] = cur;
            cur *= a[i];
        }
        cur = 1;
        //再计算i右边的所有数相乘
        for (int i = a.length - 1; i > -1; i--) {
            result[i] *= cur;
            cur *= a[i];
        }
        return result;
    }

    /**
     * 斐波那契数列 非递归写法
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
//        return fib(n-1)+ fib(n-2);
        int first = 0;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public int minArray(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        for (int i = 0; i < numbers.length; i++) {
            if (i + 1 < numbers.length && numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }

        if (numbers[0] < numbers[1]) {
            return numbers[0];
        }

        if (numbers[numbers.length - 1] < numbers[numbers.length - 2]) {
            return numbers[numbers.length - 1];
        }
        return numbers[0];
    }

    /**
     * 把数组排成最小的数
     * 利用快速排序 从小到大
     * x+y > y+x  则x>y
     *
     * @param nums
     * @return
     */
    public static void minNumber(int[] nums) {
        String[] numString = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numString[i] = nums[i] + "";
        }
        minNumberHelper(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void minNumberHelper(int[] numString, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left, j = right;
        int temp = numString[left];
        while (i < j) {
            //找到比temp小的
            while (i < j && Integer.parseInt(temp + "" + numString[j]) <= Integer.parseInt(numString[j] + "" + temp)) {
                j--;
            }
            //找到比temp大的
            while (i < j && Integer.parseInt(temp + "" + numString[i]) >= Integer.parseInt(numString[i] + "" + temp)) {
                i++;
            }
            if (i < j) {
                int x = numString[j];
                numString[j] = numString[i];
                numString[i] = x;
            }
        }
        numString[left] = numString[i];
        numString[i] = temp;
        minNumberHelper(numString, left, i - 1);
        minNumberHelper(numString, i + 1, right);
    }

    /**
     * 数组中数字出现的次数   找出只出现一次的两个数字
     * 时间复杂度为0（n） 空间复杂度为o(1)
     * 相同的值进行异或结果为0，不相同的值进行异或操作结果不为0
     * 0与任何值进行异或为该值
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            //所有的值进行异或操作
            ret ^= nums[i];
        }
        int div = 1;
        while ((ret & div) == 0) {
            //找到ret中为1 的那位
            //左移一位
            div = div << 1;
        }

        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((div & nums[i]) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }

    /**
     * x的n次方
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n > 0) {
            if (n == 1) {
                return x;
            }
            return x * myPow(x, n - 1);
        } else if (n < 0) {
            if (n == -1) {
                return 1 / x;
            }
            return 1 / x * myPow(x, n + 1);
        } else {
            return 0;
        }
    }

    /**
     * n个骰子
     *
     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        double[] result = new double[5 * n + 1];
        return null;
    }


    /**
     * 最大公约数  欧你几得算法
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getCommonDivide(int a, int b) throws Exception {
        if (a <= 0 || b <= 0) {
            throw new Exception("inputs cannot be zero");
        }

        //求余
        int max = a > b ? a : b;
        int min = a > b ? b : a;
        int temp = max % min;
        if (temp == 0) {
            return min;
        }
        return getCommonDivide(temp, min);
    }

    /**
     * 最大公约数  相减法
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getCommonDivide2(int a, int b) throws Exception {
        if (a <= 0 || b <= 0) {
            throw new Exception("inputs cannot be zero");
        }

        //求余
        int max = a > b ? a : b;
        int min = a > b ? b : a;
        int temp = max - min;
        if (temp == 0) {
            return min;
        }
        return getCommonDivide(temp, min);
    }

    /**
     * 最大公约数 移位法
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getCommonDivide3(int a, int b) throws Exception {
        if (a <= 0 || b <= 0) {
            throw new Exception("inputs cannot be zero");
        }
        if (a == b) {
            return a;
        }


        if ((a & 1) == 0 && (b & 1) == 0) {
            //都是偶数
            return getCommonDivide3(a >> 1, b >> 1) << 2;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return getCommonDivide3(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            return getCommonDivide3(a, b >> 1);
        } else {
            int max = a > b ? a : b;
            int min = a > b ? b : a;
            return getCommonDivide3(max - min, min);
        }
    }

    /**
     * 2的整数幂
     *
     * @param number
     * @return
     */
    public static boolean isPowerOf2(int number) {
        return (number & number - 1) == 0;
    }

    /**
     * 获得全排列的下一个数
     *
     * @param number
     * @return
     */
    public static int[] findNearestNumber(int[] number) {
        /**
         * 1、从个位开始，找出数列的逆序部分的前一位
         * 2、找出逆序中最小的数字a
         * 3、将a与逆序部分的前面一个数交换
         * 4、将剩下的部分重新从小到大排序
         */

        //1
        int start = 0;
        for (int i = number.length - 1; i > 0; i--) {
            if (number[i] > number[i - 1]) {
                start = i;
                break;
            }
        }
        //数组都是正序，没有结果
        if (start == 0) {
            return null;
        }


        int[] result = Arrays.copyOf(number, number.length);
        //

        return null;
    }

    /**
     * 获得全排列的下一个数
     *
     * @param number
     * @return
     */
    public static String removeDigits(String number, int k) {
        String result = number;
        boolean hasCut = false;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < result.length() - 1; j++) {
                if (j + 1 < result.length() && result.charAt(j) > result.charAt(j + 1)) {
                    result = result.substring(0, j) + result.substring(j + 1);
                    hasCut = true;
                    break;
                }
            }
        }
        if (!hasCut) {
            return result.substring(0, result.length() - 1);
        }
        System.out.println(result);

        while (result.charAt(0) == '0') {
            result = result.substring(1);
        }
        if (result.length() == 0) {
            return "0";
        }
        return result;
    }

    public static int[] addBigNumber(String a, String b) {
        int length = a.length() > b.length() ? a.length() : b.length();
        int[] resultArray = new int[length + 1];
        int[] aArray = new int[length + 1];
        int[] bArray = new int[length + 1];
        for (int i = 0; i < a.length(); i++) {
            aArray[i] = a.charAt(a.length() - i - 1) - '0';
        }

        for (int i = 0; i < b.length(); i++) {
            bArray[i] = b.charAt(b.length() - i - 1) - '0';
        }

        int data = 0;
        for (int i = 0; i < length + 1; i++) {
            int temp = aArray[i] + bArray[i] + data;

            if (temp > 10) {
                data = 1;
                resultArray[i] = temp - 10;
                continue;
            }
            data = 0;
            resultArray[i] = temp;
        }
        return resultArray;
    }

    /**
     * 寻找出现次数大于2/n的数
     * 摩尔投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int data = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == data) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    data = nums[i];
                    count++;
                }
            }
        }
        return data;
    }

    /**
     * 找出只出现一次的数字，其余都是两次
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }

        return result;
    }

    /**
     * 合并排序的数组
     * 后向遍历，最大的放在A的最后
     *
     * @param A
     * @param m 数组a的有效长度
     * @param B
     * @param n 数组b的长度
     */
    public static int[] merge(int[] A, int m, int[] B, int n) {
        int index = m + n - 1;

        int indexA = m - 1;
        int indexB = n - 1;
        int temp;
        while (indexA >= 0 || indexB >= 0) {
            if (indexA == -1) {
                temp = B[indexB--];
            } else if (indexB == -1) {
                temp = A[indexA--];
            } else if (A[indexA] > B[indexB]) {
                temp = A[indexA--];
            } else {
                temp = B[indexB--];
            }
            A[index--] = temp;
        }
        return A;
    }

    /**
     * 寻找数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashMap1 = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            hashMap1.put(nums1[i],nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (hashMap1.containsKey(nums2[i])){
                set.add(nums2[i]);
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            result[index++]=iterator.next();
        }
        return result;
    }
}