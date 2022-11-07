package com.qks.mybatisplusdemo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-24 10:29
 */
@Data
@TableName("name")
public class User {

    @TableId("id")
    private Long userId;

    @TableField("name")
    private String name;

    private Integer age;

    private String email;
}
