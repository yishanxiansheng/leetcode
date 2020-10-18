package com.cmrh.msp.android.msp.msp;

/**
 * @author hesf001
 * @description: 排序
 * @date :2020/10/16 10:10
 */
public class SortUtil {

    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
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
     * 快速排序(从大到小)
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
     * 插入排序 从小到大
     *
     * @param array
     */
    public static int[] insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < i ; j++) {
                if (array[j] > array[i]) {
                    int temp = array[i];
                    for (int k = i; k > j ; k--) {
                        array[k] = array[k - 1];
                    }
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
