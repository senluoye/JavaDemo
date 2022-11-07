package com.qks.anotation.another;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * 测试类
 * 这里只做一次测试，算是展示获取注解属性的一个小demo
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        initUser(user);

        // 年龄为0，校验为通过情况
        printResult(checkUser(user));

        // 重新设置年龄，校验通过情况
        user.setAge(22);
        printResult(checkUser(user));
    }

    /**
     * 这里只初始化User的性别
     * @param user
     * @throws IllegalAccessException
     */
    static void initUser(User user) throws IllegalAccessException {
        // 获取User类中所有的属性(getFields无法获得private属性)
        Field[] fields = User.class.getDeclaredFields();

        // 遍历所有属性
        for (Field field : fields) {
            // 如果属性上有此注解，则进行赋值操作
            if (field.isAnnotationPresent(InitSex.class)) {
                InitSex init = field.getAnnotation(InitSex.class);
                ReflectionUtils.makeAccessible(field);

                // 设置属性的性别值
                field.set(user, init.sex().toString());
                System.out.println("完成属性值的修改，修改值为:" + init.sex().toString());
            }
        }
    }

    static boolean checkUser(User user) throws IllegalAccessException {
        // 获取User类中所有的属性(getFields无法获得private属性)
        Field[] fields = User.class.getDeclaredFields();
        boolean result = true;

        // 遍历所有属性
        for (Field field : fields) {
            // 如果属性上有此注解，则进行赋值操作
            if (field.isAnnotationPresent(ValidateAge.class)) {
                ValidateAge validateAge = field.getAnnotation(ValidateAge.class);
                ReflectionUtils.makeAccessible(field);

                int age = (int) field.get(user);
                System.out.println(Integer.toString(age));
                if (age < validateAge.min() || age > validateAge.max()) {
                    result = false;
                    System.out.println("年龄值不符合条件");
                }
            }
        }
        return result;
    }

    static void printResult(boolean checkResult) {
        if (checkResult) {
            System.out.println("校验通过");
        } else {
            System.out.println("校验未通过");
        }
    }
}
