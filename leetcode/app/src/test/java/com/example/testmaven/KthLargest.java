package com.example.testmaven;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 利用小顶堆
 * @author heshufan
 * @date 2021/2/11
 */

class KthLargest {
    int k = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }
}
