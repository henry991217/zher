package com.zher.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zher.Entity.User;
import com.zher.Mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    public boolean saveUser(User user) {

        return saveOrUpdate(user);
    }
}