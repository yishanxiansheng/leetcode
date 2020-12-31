package com.example.testmaven;

import java.util.HashSet;

import static com.example.testmaven.PrintUtil.print;


/**
 * @author heshufan
 * @date 2020-12-29
 *
 * ==对于基本数据类型，比较值 对于对象，比较堆的地址
 * equals 如果不重写，比较的是堆的地址 和==一样
 */
public class TestJava {

    public static void main(String[] args) {
        String str1 = "heshufan";

        //创建了一个对象str2 ,指向"heshufan"这个字符串
        String str2 = new String("heshufan");

        print(str1 == str2);
        print(str1.equals(str2));

        TestJava testJava = new TestJava();
        TestJava testJava1 = testJava;
        TestJava testJava2 = new TestJava();

        print(testJava == testJava1);
        print(testJava == testJava2);
        print(testJava.equals(testJava2));

        HashSet<TestJava> hashSet = new HashSet<>();

        //hashSet 不能放入一样的对象
        hashSet.add(testJava);
        hashSet.add(testJava);
        print(hashSet.size());

        int a = '9'-'0';

        print(a);
        print(Integer.valueOf('9'));

    }
}
