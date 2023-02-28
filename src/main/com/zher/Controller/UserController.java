package com.zher.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zher.Entity.ERData;
import com.zher.Entity.User;
import com.zher.Mapper.UserMapper;
import com.zher.Service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserMapper user;
    @Autowired
    private UserService userService;

// 查询所有数据
    @GetMapping("/getUser")
    public List<User> getuser()
    {
        return user.findAll();
    }
//新增和修改
    @PostMapping
    public boolean addOrSave(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

// 删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id)
    {return user.deleteById(id);}

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue="") String account) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
       if((!"".equals(account))) {objectQueryWrapper.like("account",account);}//按datetime搜索
        return userService.page(page,objectQueryWrapper);
    }

}
