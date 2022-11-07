package com.qks.demo.springbootsecurity.mapper;

import com.qks.demo.springbootsecurity.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 15:11
 */
@Mapper
public interface UserMapper {
    // 查询用户
    UserDTO selectUserByUsername(@Param("username") String username);
}
