package cn.zwq.mybatis-plus.service.impl;

import cn.zwq.mybatis-plus.entity.User;
import cn.zwq.mybatis-plus.mapper.UserMapper;
import cn.zwq.mybatis-plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwq
 * @since 2022-10-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
