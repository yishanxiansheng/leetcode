package com.example.testmaven;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author heshufan
 * @date 2021/2/18
 */

public class TestArray2 {
    public static void main(String[] args) {
        longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
        reversePairs(new int[]{1, 2, 1, 2, 1});
        System.out.println(Arrays.toString(findRepeatNumber2(new int[]{1, 3, 0, 4, 2})));

        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
    }


    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = T.length - 1; i >= 0; i--){
            if(deque.isEmpty()){
                res[i] = 0;
                deque.push(i);
                continue;
            }

            while(!deque.isEmpty() && T[deque.peek()] < T[i]){
                deque.pop();
            }
            res[i] = deque.isEmpty()? 0:deque.peek() - i;
            deque.push(i);
        }
        return res;
    }

    /**
     * 数组中的逆序对
     *
     * @param nums
     * @return
     */
    private static int res = 0;

    public static int reversePairs(int[] nums) {
        mergerHelper(nums, 0, nums.length - 1);
        return res;
    }

    private static void mergerHelper(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        mergerHelper(nums, left, middle);
        mergerHelper(nums, middle + 1, right);
        merge(nums, left, right, middle);
    }

    public static void merge(int[] nums, int left, int right, int middle) {
        int i = left;
        int j = middle + 1;
        int[] temp = new int[right - left + 1];
        int index = 0;
        while (i <= middle && j <= right) {
            if (nums[i] > nums[j]) {
                //核心点，代表i以及i后面的值都比num[i]要大，都是逆序
                res += middle - i + 1;
                temp[index++] = nums[j];
                j++;
            } else {
                temp[index++] = nums[i];
                i++;
            }
        }
        while (i <= middle) {
            temp[index++] = nums[i];
            i++;
        }
        while (j <= right) {
            temp[index++] = nums[j];
            j++;
        }
        index = 0;
        while (left <= right) {
            nums[left++] = temp[index++];
        }
    }

    /**
     * 有多少小于当前数字的数字
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] acount = new int[100];
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            acount[nums[i]]++;
        }

        for (int i = 1; i < 100; i++) {
            acount[i] += acount[i - 1];
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = acount[nums[i] - 1];
        }
        return res;
    }

    /**
     * 最大连续1的个数
     * 滑动窗口
     *
     * @param A
     * @param K
     * @return
     */
    public static int longestOnes(int[] A, int K) {
        //滑动窗口的左边
        int left = 0;
        //滑动窗口中0的个数
        int num = 0;
        int max = 0;
        if (A.length <= K) {
            return A.length;
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                max = Math.max(i - left + 1, max);
                continue;
            } else if (A[i] == 0 && num < K) {
                num++;
            } else if (A[i] == 0 && num == K) {
                //找到第一个不为0的值;
                while (left < i && A[left] != 0) {
                    left++;
                }
                num--;
            }
            max = Math.max(i - left + 1, max);
        }
        return max;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
            max = Math.max(max, map.get(nums[i])[0]);
        }
        int min = nums.length;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] value = entry.getValue();
            if (value[0] == max) {
                min = Math.min(min, value[2] - value[1] + 1);
            }
        }
        return min;
    }

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 3 != 0) {
            return false;
        }
        int a = sum / 3;
        int partSum = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            partSum += arr[i];
            if (partSum == a) {
                partSum = 0;
                index++;
            }
        }
        return index == 3;
    }

    /**
     * 数组中重复的数字
     * 计数排序
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return 0;
    }

    /**
     * 加强版计数排序
     * 计数排序
     * 数据范围为0~n-1,数组长度为n，每个数据都不重复
     *
     * @param nums
     * @return
     */
    public static int[] findRepeatNumber2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] != nums[nums[i]]) {
                    int temp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return nums;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int m = Math.abs(nums[i]);
            if (nums[m] < 0) {
                list.add(nums[i]);
            } else {
                nums[m] = -nums[m];
            }
        }
        return list;
    }

    /**
     * 数组中出现次数超过一半的数字
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int var = 0;
        int varNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (varNum == 0 && var != nums[i]) {
                var = nums[i];
            }

            if (varNum != 0 && var != nums[i]) {
                varNum--;
            }

            if (var == nums[i]) {
                varNum++;
            }
        }
        return var;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //存储每门课的入度
        int[] indegrees = new int[numCourses];

        List<List<Integer>> adjacency = new ArrayList<>();

        //BFS用队列
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());

        for(int[] cp : prerequisites) {
            //cp[1]为cp[0]学前需要学的课
            indegrees[cp[0]]++;
            //存储以cp[1]为入度的所有的课程
            adjacency.get(cp[1]).add(cp[0]);
        }
        //所有入度为0的结点入队列
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // BFS
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            //取出所有以pre为前度的值，将他们的入度减1
            for(int cur : adjacency.get(pre)){
                if(--indegrees[cur] == 0){
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0;
    }
}
