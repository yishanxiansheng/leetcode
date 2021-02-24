package com.example.testmaven;

import java.util.ArrayList;
import java.util.List;

/**
 * 位运算
 *
 * @author heshufan
 * @date 2020-12-26
 */
public class BitOpreation {


    public static void main(String[] args) {
        int a = 3, b = 4;

        /**
         * a^b = c ————> c^b = a
         */

        /**
         * 1、判断是否是偶数
         * (a&1) == 0
         */
        System.out.println((a & 1) + ":" + (b & 1));

        /**
         * 2、除以2
         * a>>1
         *
         * 乘以2
         * a<<1
         */

        System.out.println((a >> 1) + ":" + (b << 1));

        /**
         * 异或操作  一个数经过偶数次异或操作结果为0
         * 求解一堆数中出现了奇数次的数
         */
        System.out.println(2 ^ 2 ^ 2);

        /**
         * bitmap算法
         */
        MyBitmap myBitmap = new MyBitmap(128);
        //保存数字100
        myBitmap.setBit(100);
        System.out.println(myBitmap.getBit(100));
        System.out.println(myBitmap.getBit(99));
    }

    /**
     * 数组中数字出现的次数||
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] res = new int[32];
        int data = 0;
        for (int num : nums) {
            int temp = 1;
            for (int i = 32; i > -1; i--) {
                if ((num & temp) != 0) {
                    res[i]++;
                }
                temp <<= 1;
            }
        }
        //秒 比特数组转化为数字
        for (int i = 0; i < 32; i++) {
            data <<= 1;
            data += res[i] % 3;
        }
        return data;

    }

    /**
     * 颠倒二进制位
     *
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 1; i <= 32; i++) {
            int data = n & 1;
            result = result + (data << (32 - i));
            n >>= 1;
        }
        return result;
    }


    /**
     * 位图算法 用来存储数据，比如数字3,就将MyBitmap的第3位bit设置为1
     */
    public static class MyBitmap {

        //存储所有的bit位，每一个word对应64位
        private long[] words;

        //位图的大小
        private int size;

        public MyBitmap(int size) {
            this.size = size;
            this.words = new long[getWordIndex(size - 1) + 1];
        }

        //定位bitmap 中bitIndex位对应的word
        public int getWordIndex(int bitIndex) {
            //比如我要查找位图的低100位，那么100位是存在第2个word当中
            return bitIndex >> 64;
        }

        //设置bitIndex的值
        public void setBit(int bitIndex) {
            if (bitIndex < 0 || bitIndex > size - 1) {
                throw new IndexOutOfBoundsException("out of bounds");
            }

            int wordIndex = getWordIndex(bitIndex);

            words[wordIndex] |= (1L << bitIndex);
        }

        //获取bitIndex的值
        public boolean getBit(int bitIndex) {
            if (bitIndex < 0 || bitIndex > size - 1) {
                throw new IndexOutOfBoundsException("out of bounds");
            }

            int wordIndex = getWordIndex(bitIndex);

            return (words[wordIndex] & (1L << bitIndex)) != 0;
        }

    }

    public int hammingWeight(int n) {
        int acount = 0;
        while (n != 0) {
            int a = n & 1;
            if (a % 2 == 0) {
                acount++;
            }
            n = n >>> 1;
        }
        return acount;

        //优化解法
//        int res = 0;
//        while(n != 0) {
//            res++;
//            n &= n - 1;
//        }
//        return res;
    }

    /**
     * 位运算计算加法
     * 异或+（与+左移一位）
     * 直到与+左移一位结果为哦没有进位了
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        int sum = a ^ b;
        int temp = (a & b) << 1;
        if (temp != 0) {
            return getSum(sum, temp);
        }
        return sum;
    }

    /**
     * 翻转数位
     * 定义一个数组
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        int[] oneAcount = new int[32];
        int index = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                oneAcount[index]++;
            } else {
                index++;
            }
            //无符号位右移
            num = num >>> 1;
        }

        int max = 0;
        for (int i = 0; i < oneAcount.length - 1; i++) {
            max = Math.max(oneAcount[i] + oneAcount[i + 1] + 1, max);
        }
        return Math.min(max, 32);
    }

    /**
     * 判断字符串字符是否唯一
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        int num = 0;
        for (int i = 0; i < astr.length(); i++) {
            int temp = 1;
            int move = astr.charAt(i) - 'a';
            temp = temp << move;
            if ((num & temp) != 0) {
                return false;
            }
            num = num | temp;
        }
        return true;
    }

    /**
     * 数字翻译为字符串
     *
     * @param num
     * @return
     */

    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) > '2' || (s.charAt(i - 1) == '2' && s.charAt(i) > '5') || s.charAt(i - 1) == '0') {
                dp[i] = dp[i - 1];
            } else {
                if (i >= 2) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
