package com.qks.demo.springbootsecurity.dto;

import com.qks.demo.springbootsecurity.entity.Role;
import com.qks.demo.springbootsecurity.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @ClassName RoleDTO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-07 15:10
 */
@Setter
@Getter
public class RoleDTO extends Role {
    private Set<User> users;
}

