package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021/3/6
 */

class MMM {
    public static void main(String[] agrs){
        Son son = new Son();
        son.print();
    }
   static class  Person{
        int a = 1;
        public Person(){
            a();
        }
        public void a(){
            System.out.println("person:a"+a);
        }
    }

    static class  Son extends Person{
        int a = 2;
        String m = "heshufan";
        public Son(){
            super();
        }

        @Override
        public void a() {
            System.out.println("son:a"+a);
            System.out.println("son:a"+m);
        }
        public void print(){
            System.out.println("son:a"+a);
            System.out.println("son:a"+m);
        }
    }
}
