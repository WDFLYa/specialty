package nuc.edu.cn.specialtyweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nuc.edu.cn.specialtyweb.entity.Specialty;
import nuc.edu.cn.specialtyweb.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/specialties")
@Tag(name = "特产管理接口", description = "用于管理特产信息")
public class SpecialtyController {

    @Autowired
    private SpecialtyService service;

    // 查询所有特产
    @GetMapping("/selectall")
    @Operation(summary = "查询所有特产")
    public List<Specialty> getAllSpecialties() {
        return service.list();
    }

    @GetMapping("/selectbysubcategoryid")
    @Operation(summary = "根据分类查询特产")
    public List<Specialty> getSpecialtiesBySubcategory(@RequestParam Long id) {
        return service.getBySubcategoryId(id);
    }

    @PostMapping("/addspecialty")  // 声明处理HTTP POST请求的接口，路径为/addspecialty
    @Operation(summary = "添加特产")  // 使用Swagger注解添加接口描述信息
    public boolean addSpecialty(@RequestBody Specialty specialty) {  // 接收请求体中的JSON数据并映射为Specialty对象
        specialty.setCreatedAt(new Date());  // 设置特产的创建时间为当前系统时间
        specialty.setUpdatedAt(new Date());  // 设置特产的更新时间为当前系统时间（新增时与创建时间一致）
        return service.save(specialty);  // 调用Service层方法保存特产信息到数据库，返回保存结果
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询特产")
    public IPage<Specialty> getSpecialtiesByPage(@RequestParam(defaultValue = "1") int current,
                                                 @RequestParam(defaultValue = "12") int pageSize) {
        Page<Specialty> page = new Page<>(current, pageSize);
        return service.page(page);  // 调用 MyBatis-Plus 提供的分页方法
    }

    @GetMapping("/pagebysub")
    @Operation(summary = "分页查询子分类下的特产")
    public IPage<Specialty> getBySubcategoryPaged(@RequestParam Long subId,
                                                  @RequestParam(defaultValue = "1") int current,
                                                  @RequestParam(defaultValue = "12") int pageSize) {
        Page<Specialty> page = new Page<>(current, pageSize);
        QueryWrapper<Specialty> wrapper = new QueryWrapper<>();
        wrapper.eq("subcategory_id", subId);
        return service.page(page, wrapper);
    }

    @GetMapping("/detail")
    @Operation(summary = "根据特产ID获取详情")
    public Specialty getSpecialtyDetail(@RequestParam Integer id) {
        return service.getById(id);
    }


    @DeleteMapping("/delete")  // 声明处理HTTP DELETE请求的接口，路径为/delete
    @Operation(summary = "根据ID删除特产")  // 使用Swagger注解添加接口描述信息
    public boolean deleteSpecialty(@RequestParam Integer id) {  // 从请求参数中获取要删除的特产ID
        return service.removeById(id);  // 调用Service层方法根据ID删除特产信息，返回删除结果
    }
    @PutMapping("/update")
    @Operation(summary = "更新特产信息")
    public boolean updateSpecialty(@RequestBody Specialty specialty) {
        specialty.setUpdatedAt(new Date());
        return service.updateById(specialty);
    }

}
