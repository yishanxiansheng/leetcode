package com.example.testmaven;

import android.app.NotificationChannel;
import android.app.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author heshufan
 * @date 2020-12-30
 */
public class LRUCache {

    //LinkedHashMap 先存的放在前面，后存的放在后面,所以不经常使用的放前面，经常使用的放后面
    private Map<Integer, Integer> map = new LinkedHashMap<>();
    private int mCapacity;

    public LRUCache(int capacity) {
        mCapacity = capacity;
    }

    public static void main(String[] args) {
        int[] a = new int[10000000];
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        PrintUtil.print(lruCache.get(2));
        lruCache.put(5, 5);
        PrintUtil.print(lruCache.get(1));
        PrintUtil.print(lruCache.get(3));
        PrintUtil.print(lruCache.get(4));
    }

    public int get(int key) {
        if (map.keySet().contains(key)) {
            int value = map.get(key);
            map.remove(key);
            // 保证每次查询后，都在末尾
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.keySet().contains(key)) {
            map.remove(key);
        } else if (map.size() == mCapacity) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();

            // int firstKey = map.e***ySet().iterator().next().getValue();
            // map.remove(firstKey);
        }
        map.put(key, value);
    }
}
