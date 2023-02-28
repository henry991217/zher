package com.zher.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zher.Entity.ERData;
import com.zher.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ERDataMapper extends BaseMapper<ERData> {
    @Select("SELECT * from erdata")
    List<User> findAll();

}
