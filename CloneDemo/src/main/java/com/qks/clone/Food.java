package com.qks.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-08 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food implements Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = -3443815804346831432L;

    private String name;

    @Override
    public Food clone() {
        try {
            return (Food) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
