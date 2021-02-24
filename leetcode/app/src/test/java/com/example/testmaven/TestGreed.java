package com.example.testmaven;

/**
 * @author heshufan
 * @date 2021/2/19
 */

public class TestGreed {

    public static void main(String[] args) {

    }

    /**
     * 种花问题
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed.length == 1 && n == 1) {
            return flowerbed[0] != 1;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 && flowerbed[i + 1] == 0) {
                    n--;
                } else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
                    n--;
                } else if (i > 0 && i < flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    n--;
                }
                if (n <= 0) {
                    return true;
                }
            }
        }
        return n <= 0;
    }
}
