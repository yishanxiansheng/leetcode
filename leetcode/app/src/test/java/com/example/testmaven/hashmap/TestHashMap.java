package com.example.testmaven.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author heshufan
 * @date 2021/2/1
 */

class TestHashMap {
    public static void main(String[] args) {
        Map<Person, String> hashMap = new HashMap<>();
        Person person1 = new Person("heshufan", "male");
        Person person2 = new Person("fenjuan", "female");
        hashMap.put(person1, "handsome");
        hashMap.put(person2, "beauterful");
        //不重写可以正常拿到值，因为person1与person2内存地址不同，所以存入的key不一定
        System.out.println(hashMap.get(person1));
        System.out.println(hashMap.get(person2));

        //不重写无法拿到值，虽然这两个person本质上是一样的
        System.out.println(hashMap.get(new Person("heshufan", "male")));


        Map<Company, String> hashMap2 = new HashMap<>();
        Company company1 = new Company("ali", "hangzhou");
        Company company2 = new Company("tencent", "shenzhen");
        hashMap2.put(company1, "handsome");
        hashMap2.put(company2, "beauterful");

        //重写，可以拿到值
        System.out.println(hashMap2.get(company1));
        System.out.println(hashMap2.get(company2));

        //重写、可以拿到值
        System.out.println(hashMap2.get(new Company("ali", "hangzhou") ));


    }

    static class Person {
        private String age;
        private String sex;

        public Person(String age, String sex) {
            this.age = age;
            this.sex = sex;
        }
    }

    static class Company {
        private String name;
        private String address;

        public Company(String name, String address) {
            this.name = name;
            this.address = address;
        }

        //现在是自动帮助复写两个方法
        @Override
        public boolean equals(Object o) {
            //默认是比较内存的值，重写一般是判断内容是否相等
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Company company = (Company) o;
            return Objects.equals(name, company.name) &&
                    Objects.equals(address, company.address);
        }

        @Override
        public int hashCode() {
            //hashcode，默认的是对象在内存中的地址转化为int值返回
            return Objects.hash(name, address);
        }
    }
}
