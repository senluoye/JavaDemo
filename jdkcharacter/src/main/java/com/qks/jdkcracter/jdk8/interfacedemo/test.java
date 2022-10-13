package com.qks.jdkcracter.jdk8.interfacedemo;

/**
 * @ClassName Dessert
 * @Description 继承的解耦有同名的方法，且接口之间没有继承关系，则子类必须重写
 * <p>子类可以通过this调用父接口的default方法 </p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 18:20
 */
public class test implements InterfaceDemo, InterfaceDemo2{

    @Override
    public void a() {
        System.out.println("test-a");
    }

    public void c() {
        this.b();
    }

    public static void main(String[] args) {
        test t = new test();
        t.a();
        t.c();
    }
}
