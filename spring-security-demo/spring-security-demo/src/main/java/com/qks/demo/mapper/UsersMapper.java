package com.qks.demo.mapper;

import com.qks.demo.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-21 10:49
 */
@Mapper
public interface UsersMapper {

    @Select("select * from users where username = #{username}")
    Users selectByUsername(String username);
}
