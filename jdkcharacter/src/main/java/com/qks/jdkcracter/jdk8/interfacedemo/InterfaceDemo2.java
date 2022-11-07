package com.qks.jdkcracter.jdk8.interfacedemo;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 18:19
 */
public interface InterfaceDemo2 {
    default void a() {
        System.out.println("InterfaceDemo2-a");
    }
}