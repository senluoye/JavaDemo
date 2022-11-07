package com.qks.jdkcracter.jdk8.interfacedemo;

/**
 * @ClassName Dessert
 * @Description JDK8新特性：接口
 * <p>1.default修饰的方法，是普通实例方法，可以用this调用，可以被子类继承、重写。</p>
 * <p>2.static修饰的方法，使用上和一般类静态方法一样。但它不能被子类继承，只能用Interface调用。</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 17:43
 */
public interface InterfaceDemo {
    static void a() {
        System.out.println("InterfaceDemo-a");
    }

    default void b() {
        a();
        System.out.println("InterfaceDemo-b");
    }
}


