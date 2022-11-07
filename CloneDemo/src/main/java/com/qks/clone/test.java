package com.qks.clone;

import org.junit.jupiter.api.Test;

/**
 * @ClassName
 * @Description 深拷贝与浅拷贝 案例
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-08 16:30
 */
public class test {

    /**
     * 通过重写clone方法实现深克隆
     */
    @Test
    public void test() {
        Person p1 = new Person(1, "asd", new Food("shit"));
        Person p2 = p1.clone();
        p1.getFood().setName("newShit");
        System.out.println(p1);
        System.out.println(p2);
    }

    /**
     * 序列化实现深克隆
     */
    @Test
    public void test2() {
        Person p1 = new Person(12, "acs", new Food("shit6"));
        Person p2 = (Person) p1.cloneBySerializable();
        p1.getFood().setName("new Shit");
        System.out.println(p1);
        System.out.println(p2);
    }
}
