package com.zher.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("user")
public class User {
 @TableId(type = IdType.AUTO)
 private Integer id;
 private String account;
 @JsonIgnore
 private  String password;
}
