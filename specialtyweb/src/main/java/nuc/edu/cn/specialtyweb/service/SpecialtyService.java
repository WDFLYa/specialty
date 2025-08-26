package nuc.edu.cn.specialtyweb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import nuc.edu.cn.specialtyweb.entity.Specialty;

import java.util.List;

public interface SpecialtyService extends IService<Specialty> {
    List<Specialty> getBySubcategoryId(Long id);
}