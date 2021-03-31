package com.example.testmaven.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heshufan
 * @date 2021/3/3
 */

class Test {
    public int[] LRU (int[][] operators, int k) {
        // write code here
        List<Integer> list = new ArrayList<>();
        LRUCache cache = new LRUCache(k);
        for(int i =0;i<operators.length;i++){
            int[] data = operators[i];
            if(data[0]==1){
                cache.add(data[1],data[2]);
            }else if (data[0]==2){
                int a = cache.get(data[1]);
                list.add(a);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]= list.get(i);
        }
        return res;
    }

    class LRUCache{
        private HashMap<Integer,Integer> map = new LinkedHashMap<>();
        private int MAX_SIZE = 0;

        public LRUCache(int size){
            MAX_SIZE = size;
        }

        public void add(int key,int value){
            if(map.containsKey(key)){
                map.remove(key);
            }else if(map.size() == MAX_SIZE){
                Iterator<Map.Entry<Integer,Integer>> iterator = map.entrySet().iterator();
                iterator.next();
                iterator.remove();
            }
            map.put(key,value);
        }

        public int get(int key){
            if(map.containsKey(key)){
                int value = map.get(key);
                map.remove(key);
                map.put(key,value);
                return value;
            }
            return -1;
        }
    }
}
