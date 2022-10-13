package com.qks.jdkcracter.jdk8.functiondemo;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 21:11
 */
public class NewKeyWord extends LambdaSuper{
    public static LambdaInterface staticF() {
        return () -> System.out.println("staticF");
    }

    public LambdaInterface f() {
        return () -> System.out.println("f");
    }

    void show() {
        //1.调用静态函数，返回类型必须是functional-interface
        LambdaInterface t = NewKeyWord::staticF;
        t.f();

        //2.实例方法调用
        NewKeyWord newKeyWord = new NewKeyWord();
        LambdaInterface lambdaInterface = newKeyWord::f;
        lambdaInterface.f();

        //3.超类上的方法调用
        LambdaInterface superf = super::sf;
        superf.f();

        //4. 构造方法调用
        LambdaInterface tt = LambdaSuper::new;
        tt.f();
    }

    public static void main(String[] args) {
        NewKeyWord newKeyWord = new NewKeyWord();
        newKeyWord.show();
    }
}

class LambdaSuper {
    LambdaInterface sf() {
        return () -> System.out.println("sf");
    }
}