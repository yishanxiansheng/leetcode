package com.example.testmaven.thread.blockqueue;

/**
 * @author heshufan
 * @date 2021/3/6
 */

public class Outer {

    public static void main(String[] args){
        Outer outer = new Outer();
        Inner inner = outer.new Inner();
        inner.visit(outer);
    }
    private String ss = "ss";
    private void print(){
        System.out.println(ss);
    }

    private static void access(Outer outer){
        outer.print();
    }

    class Inner{
        public void print(){
            System.out.println(ss);
        }

        public void visit(Outer outer){
            outer.access(outer);
        }
    }
}
