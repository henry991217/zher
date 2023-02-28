package com.zher.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zher.Config.Result;
import com.zher.Entity.ERData;
import com.zher.Mapper.ERDataMapper;
import com.zher.Service.ERDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.zher.Config.Result.OK;

@RestController
@RequestMapping("/ERData")
public class ERDataController {

    @Autowired
    ERDataMapper ERDataMapper;
    @Autowired
    private ERDataService ERDataService;

    //    增改
    @PostMapping
    public Result<?> getData(@RequestBody ERData data) {
//        ERDataMapper.insert(data);
        ERDataService.saveData(data);
        return Result.OK();
    }

    //    更新
    @PutMapping
    public Result<?> update(@RequestBody ERData data) {

        ERDataService.update();
        return Result.OK();
    }

    //    通过id删除数据
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return ERDataService.removeById(id);
    }

    //获取数据库所有数据
    @GetMapping("/getData")
    public List<ERData> getData() {
        return ERDataService.list();
    }

    //    mp分页插件
    @GetMapping("/page")
    public IPage<ERData> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String datetime,
                                  @RequestParam(defaultValue = "") String cnytohkd, @RequestParam(defaultValue = "") String hkdtocny) {
        IPage<ERData> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ERData> objectQueryWrapper = new QueryWrapper<>();
        if ((!"".equals(datetime))) {
            objectQueryWrapper.like("datetime", datetime);
        }//按datetime搜索
        if ((!"".equals(cnytohkd))) {
            objectQueryWrapper.like("cnytohkd", cnytohkd);
        }//按下行搜索
        if ((!"".equals(hkdtocny))) {
            objectQueryWrapper.like("hkdtocny", hkdtocny);
        }//按上行搜索
        objectQueryWrapper.orderByDesc("id");
        return ERDataService.page(page, objectQueryWrapper);
    }
}
