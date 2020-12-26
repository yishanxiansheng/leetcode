package com.example.testmaven;

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
        quickSort2(array3);
        System.out.println(Arrays.toString(array3));
    }


    /**
     * 从小到大
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
        for (int i = start+1; i <= end; i++) {
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


}
