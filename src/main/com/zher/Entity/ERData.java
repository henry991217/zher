package com.zher.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@TableName("erdata")
@Data
public class ERData {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String datetime;
    private String hkdtocny;
    private String cnytohkd;

}
