package com.qks.demo.springbootsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qks.demo.springbootsecurity.entity.UserInfo;
import com.qks.demo.springbootsecurity.service.UserInfoService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserInfoMapper
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 11:33
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("select * from user_info where username = #{username}")
    UserInfo getUserInfoByUsername(String username);

    // 插入用户
    @Insert("insert into user_info(username, password, role) value(#{username}, #{password}, #{role})")
    int insertUserInfo(UserInfo userInfo);

    // 修改密码
    @Update("update user_info set password = #{newPwd} where username = #{username}")
    int updatePwd(String username, String newPwd);
}
