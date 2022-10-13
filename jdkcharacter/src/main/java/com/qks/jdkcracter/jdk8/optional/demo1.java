package com.qks.jdkcracter.jdk8.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @ClassName Dessert
 * @Description 在开发过程中时常需要判断是否存在空指针的问题，Optional就是为此而生
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-20 11:48
 */
public class demo1 {
    public static void main(String[] args) {
        // 传统判断NPE的方法
        Zoo zoo = getZoo();
        if(zoo != null){
            Dog dog = zoo.getDog();
            if(dog != null){
                int age = dog.getAge();
                System.out.println(age);
            }
        }

        // Optional 判断NPE的方法
        // ofNullable 方法是创建 Optional 的方式之一
        Optional.ofNullable(zoo)
                .map(o -> o.getDog())
                .map(d -> d.getAge())
                .ifPresent(age -> System.out.println(age));
    }

    public static Zoo getZoo(){
        return new Zoo();
    }
}

@Data
class Zoo {
    private Dog dog;
}

@Data
class Dog {
    private int age;
}