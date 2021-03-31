package com.noodle.leetcode.rxjava;

/**
 * @author heshufan
 * @date 2021/3/15
 */

public class Student {
    private String name;
    private Course[] courses;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Course[] getCourses() {
        return courses;
    }

    public class Course{
        public String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
