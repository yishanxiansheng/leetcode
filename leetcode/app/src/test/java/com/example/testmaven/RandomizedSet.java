package com.example.testmaven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author heshufan
 * 只有元素存在连续的数组中的时候才能有相同的概率返回
 * insert与remove只有hash可以做到0（1）
 * @date 2021/2/8
 */

class RandomizedSet {
    //key为数字 value为数字的索引
    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int temp = list.get(list.size() - 1);
        //这里必须用set，add是插入，元素会向后移，set是替换index的元素的值
        list.add(index, temp);
        map.put(temp, index);
        map.remove(val);
        list.remove(list.size() - 1);
        return true;

    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
