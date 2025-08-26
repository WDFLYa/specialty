package nuc.edu.cn.specialtyweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nuc.edu.cn.specialtyweb.entity.Category;
import nuc.edu.cn.specialtyweb.entity.Subcategory;
import nuc.edu.cn.specialtyweb.service.CategoryService;
import nuc.edu.cn.specialtyweb.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorys")
@Tag(name = "分类管理接口", description = "用于管理类别")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private SubcategoryService subcategoryService;    // 查询所有特产
    @GetMapping("/getById")
    @Operation(summary = "根据 ID 查询种类")
    public Category getCategoryById(@RequestParam int id) {
        return service.getById(id);
    }




    @GetMapping("/getall")
    @Operation(summary = "查询所有种类")
    public List<Category> getAllCateGory(){
        return service.list();
    }


    @GetMapping("/getByCategoryId")
    @Operation(summary = "根据一级分类 ID 查询二级分类")
    public List<Subcategory> getSubcategoryByCategoryId(@RequestParam int id) {
        LambdaQueryWrapper<Subcategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Subcategory::getCategoryId, id);
        return subcategoryService.list(queryWrapper);
    }


}

