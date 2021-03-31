package com.noodle.leetcode.rxjava;

import android.graphics.Bitmap;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author heshufan
 * @date 2021/3/14
 */

public class TestRxJava {

    //观察者
    static Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
           System.out.println(s);
        }
    };

    //被观察者写法1
    static Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            //当observable被订阅的时候，OnSubscribe的call方法会被触发
            subscriber.onNext("hello");
            subscriber.onNext("hello");
            subscriber.onNext("hello");
            subscriber.onCompleted();

        }
    });

    //被观察者写法2 等价于上面
    static Observable observable2 = Observable.just("hello","hello","hello");

    //被观察者写法3 等价于上面
    static String[]  strings = new String[]{"hello","hello","hello"};
    static Observable observable3 = Observable.from(strings);

    //观察者与被观察者建立连接

    public static void main(String[] agrs){
        //建立订阅关系，最基础，这个时候被观察者会调用call方法
        observable.subscribe(observer);


        //线程变换
        Observable.just(1, 2, 3, 4)
                //事件产生的线程
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                //事件消费的线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d("heshufan", "number:" + number);
                    }
                });

        //事件变换
        Observable.just("images/logo.png") // 输入类型 String
                //这里的map将string转化为bitmap
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
                        //showBitmap(bitmap);
                    }
                });

        //利用map，将被观察者返回的数据转化为观察者需要的数据
        //被观察者返回的是学生，观察者需要的是学生的name
        Student[] students = new Student[]{new Student("a"),new Student("b")};
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String name) {
                System.out.println(name);
            }
        };
        Observable.from(students)
                //一对一进行转化
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);

        //一对多进行转化
        Student[] students2 = new Student[]{new Student("a"),new Student("b")};
        Subscriber<Student.Course> subscriber2 = new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {
                System.out.println(course.getName());
            }
        };
        Observable.from(students2)
                //因为course是一个数组，我们需要获得一个学生的所有课程
                //第一种方式是将一个数组进行返回，观察者进行遍历打印出课程的名称
                //第二种方式是利用flatMap将课程数组包装为一个Observable，由这个Observable将课程一个
                //一个发送给观察者，那么观察者就不需要进行遍历操作
                .flatMap(new Func1<Student, Observable<Student.Course>>() {
                    @Override
                    public Observable<Student.Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber2);
    }
}
