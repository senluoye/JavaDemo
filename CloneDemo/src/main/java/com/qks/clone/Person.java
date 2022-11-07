package com.qks.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-08 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = -7710144514831611031L;

    private Integer pid;
    private String name;
    private Food food;

    /**
     * 当子类也实现了Cloneable接口后，需要在父类的clone方法内实现对子类的拷贝，才能实现子类深拷贝的效果
     * @return
     */
    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            person.setFood(person.getFood().clone());
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * 自己实现一个通过序列化实现深克隆的方法
     * @return
     */
    public Object cloneBySerializable() {
        Object object = null;
        try {
            // 这里用一个字节数组缓冲区存储序列化后的对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
