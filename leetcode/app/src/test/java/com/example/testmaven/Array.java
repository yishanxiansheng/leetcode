package com.example.testmaven;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/13 11:21
 */
public class Array {

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
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n > 0){
            if (n == 1){
                return x;
            }
            return x*myPow(x,n-1);
        }else if (n<0){
            if (n == -1){
                return 1/x;
            }
            return 1/x*myPow(x,n+1);
        }else {
            return 0;
        }
    }

    /**
     * n个骰子
     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        double[] result = new double[5*n+1];
        return null;
    }
}
