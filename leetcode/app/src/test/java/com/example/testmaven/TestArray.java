package com.example.testmaven;

import android.util.ArraySet;

import androidx.annotation.IntRange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/13 11:21
 */
public class TestArray {

    public static void main(String[] args) {

        System.out.println(Integer.valueOf('2') - '0');
        int result = Integer.valueOf("9999999999");


//        int[] array = new int[]{999999998, 999999997, 999999999};
////        minNumber(array);
//        //将application/x-www-form-urlencoded字符串转换成普通字符串
//        //采用UTF-8字符集进行解码
//        try {
//            System.out.println(URLDecoder.decode("%E5%8C%97%E4%BA%AC%E5%A4%A7%E5%AD%A6", "UTF-8"));
//            //采用GBK字符集进行解码
//            System.out.println(URLDecoder.decode("%B1%B1%BE%A9%B4%F3%D1%A7", "GBK"));
//
//            // 将普通字符串转换成application/x-www-form-urlencoded字符串
//            //采用utf-8字符集
//            System.out.println(URLEncoder.encode("北京大学", "UTF-8"));
//            //采用GBK字符集
//            System.out.println(URLEncoder.encode("北京大学", "GBK"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(getCommonDivide2(4, 6) + "");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println(removeDigits("1593212", 3));
//        System.out.println(Arrays.toString(addBigNumber("426709752318", "95481253129")));
//
//
//        System.out.println(Arrays.toString(merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3)));
//
//        int[][] a = new int[4][2];
//        a[0] = new int[]{1, 3};
//        a[1] = new int[]{2, 6};
//        a[2] = new int[]{8, 10};
//        a[3] = new int[]{15, 18};
//        merge(a);
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
     * 移动零
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int zeroCount = 0;
        //找到有多少个0
        for (int i = 0; i < nums.length; i++) {
            if (0 == nums[i]) {
                zeroCount++;
            }
        }
        //将非0 的数全部移到前面
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        //最后的全部清零
        for (int i = nums.length - 1; i >= nums.length - zeroCount; i--) {
            nums[i] = 0;
        }
    }

    /**
     * 找到所有数组中消失的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                nums[nums[i] - 1] = nums[nums[i] - 1] > 0 ? -nums[nums[i] - 1] : nums[nums[i] - 1];
            } else {
                nums[Math.abs(nums[i]) - 1] = nums[Math.abs(nums[i]) - 1] > 0 ? -nums[Math.abs(nums[i]) - 1] : nums[Math.abs(nums[i]) - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);

            }
        }
        return list;
    }


    /**
     * 最长升序子序列
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            max = Math.max(max, dp[i]);
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    /**
     * 找出重复的整数
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int[] data = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            data[nums[i]]++;
            if (data[nums[i]] > 1) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //从小到大排序
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            //只需要与result的最后一个元素进行比较
            if (result.size() == 0) {
                result.add(intervals[i]);
                continue;
            }
            int[] temp = result.get(result.size() - 1);
            if (temp[1] < intervals[i][0]) {
                result.add(intervals[i]);
            } else if (temp[1] <= intervals[i][1]) {
                temp[1] = intervals[i][1];
                result.set(result.size() - 1, temp);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    /**
     * 找出最大的三个数
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length - 1;
        int max = nums[length] * nums[length - 1] * nums[length - 2];
        if (nums[0] < 0 && nums[1] < 0) {
            max = Math.max(max, nums[0] * nums[1] * nums[length]);
        }
        return max;
    }

    /**
     * 最大连续子数组
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int max = 1;
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    max = max * nums[i];
                }
            }
        } else {


        }
        return max;
    }

    /**
     * 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //dp[i]表示前i位的最大值，包含nums[i],一般连续子序列的动态规划都需要包含第i项
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        int max = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
//            max = Math.max(max, dp[i]);
//        }
//        return max;

        //优化后的
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int data = dp;
            dp = Math.max(data + nums[i], nums[i]);
            max = Math.max(max, dp);
        }
        return max;
    }

    /**
     * 乘积最大子数组
     *
     * @param nums
     * @return
     */
    public int maxProducts(int[] nums) {
        int length = nums.length;
        int maxF = nums[0];
        int minF = nums[0];
        int result = nums[0];
        for (int i = 1; i < length; ++i) {
            int max = maxF;
            int min = minF;
            maxF = Math.max(max * nums[i], Math.max(nums[i], min * nums[i]));
            minF = Math.min(min * nums[i], Math.min(nums[i], max * nums[i]));
            result = Math.max(result, maxF);
        }

        return result;

        //优化前

//        int length = nums.length;
//        int[] maxF = new int[length];
//        int[] minF = new int[length];
//        maxF[0] = nums[0];
//        minF[0] = nums[0];
//        int result = nums[0];
//        for (int i = 1; i < length; ++i) {
//            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
//            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
//            result = Math.max(result,maxF[i]);
//        }
//
//        return result;
    }

    /**
     * 连续子序列为k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        //暴力解法
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if (sum == k) {
//                    count++;
//                    break;
//                }
//            }
//        }
//        return count;

        //优化解法  利用的是前缀和
        //pre[i]代表前i项的数据和，pre[i]-pre[j-1]==k代表从j到i的的数组的和为k
        //所以当我们知道pre[i]的时候，只需要知道有多个个pre[j]满足pre[i]-pre[j-1]==k就可以了

        Map<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        int pre = 0;
        hashMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (hashMap.containsKey(pre - k)) {
                //可能不止包含一个
                count += hashMap.get(pre - k);
            }
            hashMap.put(pre, hashMap.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    /**
     * 滑动窗口的最大值
     * 滑动窗口只需要两个指针就ok了，不需要真的弄个数组
     * 利用优先队列存储的是当前滑动窗口的值，不过是已经排好序了，每次排序的时间复杂度为logn，所以最终的
     * 时间复杂度为nlogn
     * PriorityQueue
     *
     * @param nums
     * @param k    1<=k<=nums.length
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }


    public static List<Integer> addToArrayForm(int[] A, int K) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < A.length; i++) {
            buffer.append(A[i]);
        }
        int result = Integer.valueOf(buffer.toString());
        int a = result + K;
        char[] chars = String.valueOf(a).toCharArray();
        List<Integer> lists = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            lists.add(Integer.valueOf(chars[i]));
        }
        return lists;
    }

    /**
     * 最长连续递增子序列
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int left = 0;
        if (nums.length ==1 || nums.length == 0){
            return nums.length;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i-1]) {
                left = i;
            }
            max = Math.max(i - left + 1, max);

        }
        return max;
    }
}