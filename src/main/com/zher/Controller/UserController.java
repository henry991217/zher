package com.zher.Controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zher.Config.Result;
import com.zher.Entity.ERData;
import com.zher.Entity.User;
import com.zher.Mapper.UserMapper;
import com.zher.Service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private UserService userService;

// 查询所有数据
    @GetMapping("/getUser")
    public List<User> getuser()
    {
        return userMapper.findAll();
    }
//新增和修改
    @PostMapping
    public boolean addOrSave(@RequestBody User user)
    {
        return userService.saveUser(user);
    }



    @PostMapping("/login")
    public Result<?> login(@RequestBody User user)
    {

        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getPassword,user.getPassword())
                .eq(User::getAccount,user.getAccount()));
        if(res==null) {
            return Result.error(-1, "用户不存在");
        }

        return Result.OK();

    }



// 删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id)
    {return userMapper.deleteById(id);}

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue="") String account) {
        IPage<User> page = new Page<>(pageNum, pageSize);//分页插件
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();//mp查询语句
       if((!"".equals(account))) {objectQueryWrapper.like("account",account);}//按账号搜索
        return userService.page(page,objectQueryWrapper);
    }

}
