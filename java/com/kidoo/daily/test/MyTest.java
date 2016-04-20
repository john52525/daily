package com.kidoo.daily.test;

/**
 * Created by Administrator on 2016/4/16.
 */
public class MyTest {

    private int age;

    public MyTest() {
    }

    public static void main(String args[]) {
        MyTest myTest = new MyTest();
        myTest.setAge(0);
    }

    public void setAge(int age) {
        this.age = age;
    }
}
