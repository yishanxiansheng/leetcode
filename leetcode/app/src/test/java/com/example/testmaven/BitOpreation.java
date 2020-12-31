package com.example.testmaven;

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
            if (bitIndex < 0 || bitIndex > size-1) {
                throw new IndexOutOfBoundsException("out of bounds");
            }

            int wordIndex = getWordIndex(bitIndex);

            words[wordIndex] |= (1L << bitIndex);
        }

        //获取bitIndex的值
        public boolean getBit(int bitIndex) {
            if (bitIndex < 0 || bitIndex > size-1) {
                throw new IndexOutOfBoundsException("out of bounds");
            }

            int wordIndex = getWordIndex(bitIndex);

            return (words[wordIndex] & (1L << bitIndex)) != 0;
        }

    }


}
