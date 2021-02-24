package com.example.testmaven;

import android.util.ArraySet;

import androidx.annotation.IntRange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/13 11:21
 */
public class TestArray {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(1);//integer = 1
        PrintUtil.print(integer.addAndGet(1));//打印2  integer = 2
        PrintUtil.print(integer.getAndAdd(2));//打印2 integer = 4
        PrintUtil.print(integer.get());//打印4
        PrintUtil.print(integer.compareAndSet(4,2));//打印 true integer = 2
        PrintUtil.print(integer.compareAndSet(4,2));//打印 false integer = 2
        PrintUtil.print(integer.get());// integer = 2






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

        missingNumber2(new int[]{3, 0, 1});

        subsets(new int[]{1, 2, 3});

        System.out.println(trailingZeroes(6));
        System.out.println(countPrimes(10));
        findMaxAverage(new int[]{-1}, 1);
        generateParenthesis(2);
        heightChecker(new int[]{1, 3, 4, 2, 5});
        sortedSquares(new int[]{-4, -1, 0, 3, 10});
        minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5);
    }

    /**
     * 有序数组的平方
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int res[] = new int[nums.length];
        int index = 0;
        while (left < right) {
            int a = nums[left] * nums[left] > nums[right] * nums[right] ? left++ : right--;
            res[nums.length - 1 - index] = nums[a] * nums[a];
        }
        return res;
    }

    /**
     * 最下移动人数
     *
     * @param heights
     * @return
     */
    public static int heightChecker(int[] heights) {
        int[] sorted = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            sorted[i] = heights[i];
        }
        Arrays.sort(sorted);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != sorted[i]) {
                res++;
            }
        }
        return res;
    }

    /**
     * 可获得的最大点数
     * 滑动窗口法 双指针
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int acount = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            acount += cardPoints[i];
        }
        int length = cardPoints.length;
        max = Math.max(max, acount);

        int right = k - 2;
        int left = -1;
        while (right >= -1) {
            acount = acount - cardPoints[right] + cardPoints[length + left];
            max = Math.max(max, acount);
            left--;
            right--;
        }
        return max;
    }

    /**
     * 括号生成
     * 本质是全排列+排列必须是有效序列这个限定条件
     * 回溯法
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0) {
            return list;
        }
        StringBuffer buffer = new StringBuffer();
        int layer = 0;
        dfs4(list, n, layer, buffer);
        return list;
    }

    private static void dfs4(List<String> list, int n, int layer, StringBuffer buffer) {
        if (layer == n * 2) {
            if (isEffective(buffer.toString().toCharArray())) {
                list.add(buffer.toString());
            }
            return;
        }
        buffer.append("(");
        dfs4(list, n, layer + 1, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append(")");
        dfs4(list, n, layer + 1, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
    }

    /**
     * 判断是否是有效括号序列
     *
     * @param chars
     * @return
     */
    public static boolean isEffective(char[] chars) {
        int balance = 0;
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double res = 0;
        double max = -Integer.MIN_VALUE;
        if (k > nums.length) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                res += nums[i];
                max = res;
            } else {
                res -= nums[left];
                res += nums[i];
                max = Math.max(max, res);
                left++;
            }
        }
        return (double) max / (double) k;
    }

    /**
     * 计数质数
     * 如果我们在进行顺序遍历时，每取得一个数（排除0、1），如果将它所有的倍数（排除0、1、本身）都清除，
     * 那么，剩下的数是不是必为素数
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int[] data = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            data[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            int s = 2;
            while (s * i <= n && data[i] != 0 && data[s * i] != 0) {
                data[s * i] = 0;
                s++;
            }
        }
        int result = 0;
        for (int i = 2; i <= n; i++) {
            if (data[i] != 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * 寻找阶乘后的零
     * 一个数只有在乘以2*5末尾才会增加一个0，所以我们需要寻找阶乘中有多少个10，因为每两个数会出现
     * 一个2，所以我们需要寻找5的个数
     * 每5个数出现一个5，每25个数又会额外多出一个5，每125个数会额外多出两个5
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        //5的个数
        int acount = 0;
        for (int i = 0; i <= n; i++) {
            int m = i;
            while (m >= 5) {
                if (m % 5 == 0) {
                    acount++;
                    m /= 5;
                } else {
                    break;
                }
            }
        }
        return acount;
    }


    /**
     * 螺旋矩阵
     * 采用分层法，从外往里一层一层
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int rows = matrix.length;
        int colums = matrix[0].length;
        int[] top = new int[]{0, 0};
        int[] bottom = new int[]{rows - 1, colums - 1};

        while (top[0] <= bottom[0] && top[1] <= bottom[1]) {
            //从左往右
            for (int i = top[1]; i <= bottom[1]; i++) {
                result.add(matrix[top[0]][i]);
            }

            //从上往下
            for (int i = top[0] + 1; i <= bottom[0]; i++) {
                result.add(matrix[i][bottom[1]]);
            }

            //从右往左
            //注意条件bottom[0] > top[0] 否则会出现重复
            for (int i = bottom[1] - 1; i >= top[1] && bottom[0] > top[0]; i--) {
                result.add(matrix[bottom[0]][i]);
            }

            //从下往上
            //注意条件bottom[1] > top[1] 否则会出现重复
            for (int i = bottom[0] - 1; i > top[0] && bottom[1] > top[1]; i--) {
                result.add(matrix[i][top[1]]);
            }
            top[0]++;
            top[1]++;
            bottom[0]--;
            bottom[1]--;
        }
        return result;
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
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            hashMap1.put(nums1[i], nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (hashMap1.containsKey(nums2[i])) {
                set.add(nums2[i]);
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            result[index++] = iterator.next();
        }
        return result;
    }

    /**
     * 和为s的连续正数序列
     * 双指针
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int left = 1;
        int right = 1;
        while (left < target / 2 + 1) {
            int temp = (left + right) * (right - left + 1) / 2;
            if (temp == target) {
                int[] a = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    a[i - left] = i;
                }
                list.add(a);
            } else if (temp < target) {
                right++;
            } else {
                left++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public int[] printNumbers(int n) {
        int length = (int) Math.pow(10, n) - 1;
        int[] result = new int[length];
        for (int i = 1; i <= length; i++) {
            result[i - 1] = i;
        }
        return result;
    }

    /**
     * 寻找数组的中心索引
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int[] total = new int[nums.length];
        total[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            total[i] = total[i - 1] + nums[i];
        }

        for (int i = 1; i < nums.length; i++) {
            if (total[i] - nums[i] == total[nums.length - 1] - total[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 寻找丢失的数
     *
     * @param nums
     * @return
     */
    public static int missingNumber2(int[] nums) {
        int total = nums.length * (nums.length + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            total -= nums[i];
        }
        return total;
    }

    /**
     * 两个数组的交集2
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> hashMap1 = new HashMap<>();
        int[] result = new int[nums1.length];
        int index = 0;
        for (int i = 0; i < nums1.length; i++) {
            hashMap1.put(nums1[i], hashMap1.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (hashMap1.containsKey(nums1[i])) {
                result[index++] = nums1[i];
                if (hashMap1.get(nums1[i]) == 1) {
                    hashMap1.remove(nums1[i]);
                } else {
                    hashMap1.put(nums1[i], hashMap1.get(nums1[i]) - 1);
                }
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    public int mySqrt(int x) {
        long d = x;
        for (long i = 0; i < d; i++) {
            if (i * i <= x && (i + 1) * (i + 1) > d) {
                return (int) i;
            }
        }
        return 0;
    }

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 快乐数 双指针法，不是快乐数会出现循环
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    /**
     * 是否是丑数
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        while (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) {
            if (num % 2 == 0) {
                num = num / 2;
                continue;
            }
            if (num % 3 == 0) {
                num = num / 2;
                continue;
            }
            if (num % 5 == 0) {
                num = num / 2;
                continue;
            }
        }
        if (num == 1) {
            return true;
        }

        return false;
    }

    /**
     * 有序数组的两数之和
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int data = numbers[left] + numbers[right];
            if (data < target) {
                left++;
            } else if (data > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};

            }
        }
        return null;
    }

    /**
     * 公平的糖果棒交换
     *
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sunA = Arrays.stream(A).sum();
        int sunB = Arrays.stream(B).sum();
        int delta = (sunA - sunB) / 2;
        Set<Integer> set = new HashSet<>();

        for (int a : A) {
            set.add(a);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }

    /**
     * 全排列
     * 回溯法
     * https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int length = nums.length;
        //记录nums中下标为i的数字是否被选择
        boolean[] contain = new boolean[length];
        //记录层数，一般递归到length就需要返回
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        dfs(nums, length, result, num, stack, contain);
        return result;

    }

    private void dfs(int[] nums, int length, List<List<Integer>> result, int num, Stack stack, boolean[] contain) {
        if (num == length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!contain[i]) {
                stack.add(nums[i]);
                contain[i] = true;
                dfs(nums, length, result, num + 1, stack, contain);
                //状态重置，也就是之前加入的元素需要删除
                stack.pop();
                contain[i] = false;
            }
        }
    }

    /**
     * 全排列2
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int length = nums.length;
        //记录nums中下标为i的数字是否被选择
        boolean[] contain = new boolean[length];
        //记录层数，一般递归到length就需要返回
        int num = 0;
        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        dfs3(nums, length, result, num, stack, contain);
        return result;

    }

    private static void dfs3(int[] nums, int length, List<List<Integer>> result, int num, Stack stack, boolean[] contain) {
        if (num == length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (contain[i] || (i > 0 && nums[i] == nums[i - 1]) && !contain[i - 1]) {
                continue;
            }

            if (!contain[i]) {
                stack.add(nums[i]);
                contain[i] = true;
                dfs3(nums, length, result, num + 1, stack, contain);
                //状态重置，也就是之前加入的元素需要删除
                stack.pop();
                contain[i] = false;
            }
        }

    }

    /**
     * 子集
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        //方法一 遍历
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            for (int j = 0; j < lists.size(); j++) {
                List<Integer> data = lists.get(j);
                //java的对象应用，这里需要新建
                temp.add(new ArrayList<>(data));
                data.add(nums[i]);
                temp.add(data);
            }
            lists = temp;
        }
        return lists;
    }

    /**
     * 子集
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        //方法二 回溯算法
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return lists;
        }
        //层数
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        int length = nums.length;
        dfs2(nums, num, length, stack, lists);
        return lists;
    }

    private static void dfs2(int[] nums, int num, int length, Stack<Integer> stack, List<List<Integer>> lists) {
        if (num == length) {
            lists.add(new ArrayList<>(stack));
            return;
        }
        dfs2(nums, num + 1, length, stack, lists);
        stack.add(nums[num]);
        dfs2(nums, num + 1, length, stack, lists);
        stack.pop();
    }

    /**
     * 自除数
     *
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (i < 10) {
                result.add(i);
            } else if (!String.valueOf(i).contains("0")) {
                int a = i;
                boolean isRight = true;
                while (a > 0) {
                    int m = a % 10;
                    if (i % m != 0) {
                        isRight = false;
                        break;
                    }
                    a = a / 10;
                }
                if (isRight) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    public boolean checkPossibility(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                num++;
                if (num > 1) {
                    return false;
                }
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }

    /**
     * 搜索旋转排序数组
     * 核心点在于二分法之后，怎么判断数组是否有序
     * left middle right   a[left]<a[middle]那么就是有序的
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[left] <= nums[middle]) {
                if (target >= nums[left] && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (target > nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int result = 0;
        for (Integer a : map.keySet()) {
            result += map.get(a) * (map.get(a) - 1) / 2;
        }
        return result;
    }

    /**
     * 爱吃香蕉珂珂
     *
     * @param piles
     * @param H
     * @return
     */
    public static int minEatingSpeed(int[] piles, int H) {
        int max = piles[0];
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        int left = 1, right = max;
        int res = Integer.MAX_VALUE;
        while (left <= right) {
            int speed = (left + right) / 2;
            long result = 0;
            for (int j = 0; j < piles.length; j++) {
                result += piles[j] / speed + (piles[j] % speed == 0 ? 0 : 1);
            }
            if (result <= H) {
                res = Math.min(res, speed);
                right = speed - 1;
            } else {
                left = speed + 1;
            }
        }
        return res;
    }

    /**
     * 原地删除数组的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int left = 1;
        int right = 1;
        while (left < right && right < nums.length) {
            if (nums[right] == nums[right - 1]) {
                right++;
            } else {
                nums[left] = nums[right];
                left++;
                right++;
            }
        }
        return left;
    }

    /**
     * 移除元素
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[left] != val) {
                left++;
            }
            while (nums[right] == val) {
                right--;
            }
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
        return left;
    }

    /**
     * 下一个更大的元素
     * 单调栈
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            if (deque.isEmpty() || nums2[i] < deque.peek()) {
                deque.push(nums2[i]);
                continue;
            }
            while (!deque.isEmpty() && deque.peek() < nums2[i]) {
                map.put(deque.pop(), nums2[i]);
            }
            deque.push(nums2[i]);
        }

        while (!deque.isEmpty()) {
            map.put(deque.pop(), -1);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    /**
     * 组合
     * 回溯法
     * 树状图的第i层代表选择了i个数
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        //当前的深度
        int deep = 0;
        boolean[] choose = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        combineHelper(lists, n, k, stack, deep, choose);
        return lists;
    }

    private void combineHelper(List<List<Integer>> lists, int n, int k, Stack<Integer> stack, int deep, boolean[] choose) {
        if (deep == k) {
            lists.add(new ArrayList<>(stack));
            return;
        }
        for (int i = stack.isEmpty() ? 0 : stack.peek(); i < n; i++) {
            if (!choose[i]) {
                stack.add(i);
                combineHelper(lists, n, k, stack, deep + 1, choose);
                stack.pop();
                combineHelper(lists, n, k, stack, deep + 1, choose);
            }
        }
    }

    /**
     * 航班预订表
     * 差分数组
     * diff[i] = diff[i]-diff[i-1]
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        int[] diff = new int[n];
        //构造差分数组，利用差分数组还原原来的数组
        for (int i = 0; i < bookings.length; i++) {
            diff[bookings[i][0] - 1] += bookings[i][2];
            if (bookings[i][1] != n - 1) {
                diff[bookings[i][1]] -= bookings[i][2];
            }
        }
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    /**
     * 找到所有数组中消失的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] % nums.length;
            if (index == 0) {
                index = nums.length;
            }
            nums[index - 1] += nums.length;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length) {
                res.add(i + 1);
            }
        }
        return res;
    }

    /**
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c) {
            return nums;
        }
        int l = 0, k = 0;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = nums[l][k];
                if (k == nums[0].length - 1) {
                    k = 0;
                    l += 1;
                } else {
                    k += 1;
                }
            }
        }
        return res;
    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            //找到第一个偶数
            while (left < right && left < nums.length && nums[left] % 2 != 0) {
                left++;
            }
            //找到第一个奇数
            while (left < right && right >= 0 && nums[right] % 2 == 0) {
                right--;
            }

            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        return nums;
    }

    /**
     * 最小翻转次数
     * 有几个重要的结论
     * @param A
     * @param K
     * @return
     */
    public static int minKBitFlips(int[] A, int K) {
        //差分数组,diff[i]代表第i个元素翻转次数减去第i-1个元素翻转次数
        int[] diff = new int[A.length + 1];
        int res = 0;
        int m = 0;
        for (int i = 0; i < A.length; i++) {
            m += diff[i];
            if (i == 0 && A[i] % 2 == 0) {
                diff[1] = 1;
                diff[i + K] -= 1;
                res++;
            } else {
                if ((A[i] + m) % 2 == 0) {
                    //需要翻转
                    if (i + K > A.length) {
                        return -1;
                    }
                    diff[i + 1] += 1;
                    diff[i + K] -= 1;
                    res++;
                }
            }
        }
        return res;
    }
}