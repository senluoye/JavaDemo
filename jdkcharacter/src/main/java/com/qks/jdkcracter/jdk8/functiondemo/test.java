package com.qks.jdkcracter.jdk8.functiondemo;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 20:01
 */
public class test {
    public static void main(String[] args) {
        String msg = "Hello World";
        LambdaInterface li = () -> System.out.println("Hello World");
        li.f();

        use(() -> System.out.println("Hello World"));
    }

    public static void use(LambdaInterface lambdaInterface) {
        System.out.println(lambdaInterface);
        lambdaInterface.f();
    }

}
