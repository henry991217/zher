package com.zher.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zher.Entity.ERData;
import com.zher.Entity.User;
import com.zher.Mapper.ERDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ERDataService extends ServiceImpl<ERDataMapper, ERData>{


    public boolean saveData(ERData ERdata) {

        return saveOrUpdate(ERdata);
    }
}
