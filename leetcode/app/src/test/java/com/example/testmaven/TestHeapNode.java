package com.example.testmaven;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

//堆  大顶堆：每个父结点比他的子节点大，但是左右结点谁大谁小不知道
public class TestHeapNode {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 3, 2, 6, 5, 5, 8, 9, 10};
        up(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{0, 1, 3, 2, 6, 5, 5, 8, 9, 10};
        downMiner(array2, 0, array2.length);
        System.out.println(Arrays.toString(array2));
//
//        int[] array3 = new int[]{0, 1, 3, 2, 6, 5, 5, 8, 9, 10};
//        build(array3);
//        System.out.println(Arrays.toString(array3));
//
//        int[] array4 = new int[]{0, 1, 3, 2, 6, 5, 5, 8, 9, 10};
//        heapSort(array4);
//        System.out.println(Arrays.toString(array4));
//
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));

//        lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
    }

    /**
     * 一次上浮,大的上浮
     *
     * @param heap
     */
    public static void up(int[] heap) {
        int lastIndex = heap.length - 1;
        int parentIndex = (lastIndex - 1) / 2;
        int temp = heap[lastIndex];
        while (lastIndex > 0 && heap[parentIndex] < temp) {
            heap[lastIndex] = heap[parentIndex];
            lastIndex = parentIndex;
            parentIndex = (lastIndex - 1) / 2;
        }
        heap[lastIndex] = temp;
    }

    /**
     * 一次下沉,小的下沉
     *
     * @param heap
     */
    public static void downMiner(int[] heap, int parentIndex, int length) {
        int temp = heap[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            /**
             * 1、确认左右子结点哪个更大
             * 2、查看父节点与最大的子节点比较，如果父节点小于最大的子节点，则交换，否则跳过
             */
            if (childIndex + 1 < length && heap[childIndex] < heap[childIndex + 1]) {
                childIndex++;
            }

            if (heap[childIndex] < temp) {
                break;
            }

            //将比父节点大的子节点上移
            if (heap[childIndex] > temp) {
                int data = heap[childIndex];
                heap[childIndex]= heap[parentIndex];
                heap[parentIndex] = data;
                parentIndex = childIndex;
                childIndex = parentIndex * 2 + 1;
            }
        }
    }

    /**
     * 一次下沉,大的下沉
     *
     * @param heap
     */
    public static void downMaxer(int[] heap, int parentIndex, int length) {
        int temp = heap[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            /**
             * 1、找到两个子节点最小的一个结点
             * 2、最小的结点与父节点比较
             */

            if (childIndex + 1 < length && heap[childIndex] > heap[childIndex + 1]) {
                childIndex++;
            }

            if (heap[childIndex] >= temp) {
                break;
            }

            heap[childIndex] = heap[parentIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        heap[parentIndex] = temp;
    }

    /**
     * 构造大顶堆 从最后一个非叶子节点开始做下沉操作
     */
    public static void build(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downMiner(array, i, array.length);
        }
    }

    /**
     * 堆排序 从小到大
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        /**
         * 1、先构造最大堆，大的在上小的在下
         * 2、将根结点与最后的叶子结点交换，然后将根结点进行下沉操作,
         * 下沉的深度是有效的堆的深度，不是一直下沉到数组最后的位置
         */
        build(array);
        for (int i = array.length - 1; i > 0; i--) {
            //交换一次后最大的跑到最下面，然后调整为最大堆，所以小的就需要下去
            //然后保证最大的都在上面，以便下次交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downMiner(array, 0, i);
        }
    }


    /**
     * 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        build(nums);
        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                return nums[0];
            }
            int temp = nums[nums.length - i-1];
            nums[nums.length - 1-i] = nums[0];
            nums[0] = temp;
            downMiner(nums, 0, nums.length - 1 - i);
        }
        return 0;
    }

    /**
     * 最后一块石头的重量
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < stones.length; i++) {
            queue.add(stones[i]);
        }
        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            if (a != b) {
                queue.add(Math.abs(a - b));
            }
        }
        return queue.size() == 0 ? 0 : queue.peek();
    }
}
