package com.example.testmaven;

import android.support.v4.app.INotificationSideChannel;

import java.util.Arrays;

/**
 * @author hesf001
 * @description: 排序
 * @date :2020/10/16 10:10
 */
public class SortUtil {

    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        insertSort(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        bubbleSort2(array2);
        System.out.println(Arrays.toString(array2));

        int[] array3 = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        quickSort(array3);
        System.out.println(Arrays.toString(array3));

        int[] array4 = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        countNumSort1(array4);
        System.out.println(Arrays.toString(array4));

        int[] array5 = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        System.out.println(findMaxDiff(array5));

        int[] array6 = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        System.out.println(Arrays.toString(mergeSort(array6)));


    }


    /**
     * 从小到大
     * 第一轮冒泡需要将最小的数据放在最上面
     *
     * @param array
     */
    public static void bubbleSort2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序(从大到小)
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] > array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 快速排序(从大到小) 双循环法
     *
     * @param array
     * @return
     */
    public static void quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    public static void quickSortHelper(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int temp = array[start];
        while (i < j) {
            //从右边开始找到第一个比temp大的数
            while (i < j && array[j] <= temp) {
                j--;
            }
            //从左边开始找到一个比temp小的数
            while (i < j && array[i] >= temp) {
                i++;
            }
            if (i < j) {
                int x = array[j];
                array[j] = array[i];
                array[i] = x;
            }
        }
        //将最后一个比temp大的换到首位
        array[start] = array[i];
        array[i] = temp;
        quickSortHelper(array, start, i - 1);
        quickSortHelper(array, i + 1, end);
    }

    /**
     * 单循环法 从小到大排序
     *
     * @param array
     */
    public static void quickSort2(int[] array) {
        quickSort2Helper(array, 0, array.length - 1);
    }

    public static void quickSort2Helper(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int temp = array[start];
        int mark = start;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < temp) {
                mark++;
                int a = array[mark];
                array[mark] = array[i];
                array[i] = a;
            }
        }

        array[start] = array[mark];
        array[mark] = temp;
        quickSort2Helper(array, start, mark - 1);
        quickSort2Helper(array, mark + 1, end);

    }

    /**
     * 插入排序 从小到大
     *
     * @param array
     */
    public static int[] insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i]) {
                    int temp = array[i];
                    for (int k = i; k > j; k--) {
                        array[k] = array[k - 1];
                    }
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 最小的k个数
     * 利用快速排序 从小到大排序
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        getLeastNumbersHelper(arr, 0, arr.length - 1);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public void getLeastNumbersHelper(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int temp0 = arr[left];
        while (i < j) {
            while (i < j && arr[left] <= arr[j]) {
                //找到一个比arr[left]小的
                j--;
            }
            while (i < j && arr[left] >= arr[i]) {
                //找到一个比arr[left]大的
                i++;
            }
            if (i < j) {
                int temp1 = arr[j];
                arr[j] = arr[i];
                arr[i] = temp1;
            }

        }
        arr[left] = arr[i];
        arr[i] = temp0;
        getLeastNumbersHelper(arr, left, i - 1);
        getLeastNumbersHelper(arr, i - 1, right);
    }

    /**
     * 计数排序1 最简单的
     *
     * @param array
     */
    public static int[] countNumSort1(int[] array) {
        //找出最大值跟最小值
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            max = max > array[i] ? max : array[i];
            min = min > array[i] ? array[i] : min;
        }

        int[] tempArray = new int[max - min + 1];

        //数组index等于num - min的值，index数组的值就+1
        for (int i = 0; i < array.length; i++) {
            tempArray[array[i] - min]++;
        }

        int index = 0;
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray[i]; j++) {
                array[index++] = i + min;
            }
        }
        return array;
    }

    /**
     * 计算数组最大的相邻差 最简单的
     *
     * @param array 无序数组
     */
    public static int findMaxDiff(int[] array) {
        //找出最大值跟最小值
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            max = max > array[i] ? max : array[i];
            min = min > array[i] ? array[i] : min;
        }

        int[] tempArray = new int[max - min + 1];

        //数组index等于num - min的值，index数组的值就+1
        for (int i = 0; i < array.length; i++) {
            tempArray[array[i] - min]++;
        }

        int result = 0;
        int max2 = 0;
        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i] == 0) {
                result++;
                continue;
            }
            max2 = max2 > result ? max2 : result;
            result = 0;
        }
        return max2;
    }

    /**
     * 归并排序，从小到大
     *
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums) {
        mergeHelper(nums, 0, nums.length - 1);
        return nums;
    }

    public static void mergeHelper(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeHelper(nums, left, middle);
        mergeHelper(nums, middle + 1, right);
        merge(nums, left, right, middle);
    }

    /**
     * 合并两个排好的数组
     *
     * @param nums
     * @param left
     * @param right
     * @param middle
     */
    public static void merge(int[] nums, int left, int right, int middle) {
        int i = left;
        int j = middle + 1;
        int[] temp = new int[right - left + 1];
        int index = 0;
        while (i <= middle && j <= right) {
            if (nums[i] > nums[j]) {
                temp[index++] = nums[j++];
            } else {
                temp[index++] = nums[i++];
            }
        }
        while (i <= middle) {
            temp[index++] = nums[i++];
        }

        while (j <= right) {
            temp[index++] = nums[j++];
        }
        index = 0;
        while (left <= right) {
            nums[left++] = temp[index++];
        }
    }

    /**
     * 两个栈实现队列
     * 栈a,栈b
     * 入栈 入a
     * 出栈 b不为空，b pop；b为空，a的所有元素出栈移到b，b pop
     */


    /**
     * 两个4队列实现栈
     * 队列a,队列b
     *
     */


}
