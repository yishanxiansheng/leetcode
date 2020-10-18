package com.cmrh.msp.android.msp.msp;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/13 11:21
 */
public class Array {

    public static void main(String[] args) {
        int[] array = new int[]{999999998,999999997,999999999};
        minNumber(array);
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
}
