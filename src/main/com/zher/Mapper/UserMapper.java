package com.zher.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zher.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
@Select("SELECT * from user")
    List<User> findAll();


@Insert("INSERT into user(account,password) VALUES(#{account},#{password});")
     int add(User user);

@Update("update user set account=#{account},password=#{password} where id =#{id};")
     int update(User user);
}
