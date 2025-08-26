package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.Subcategory;
import nuc.edu.cn.specialtyweb.mapper.SubcategoryMapper;
import nuc.edu.cn.specialtyweb.service.SubcategoryService;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryServiceImpl extends ServiceImpl<SubcategoryMapper, Subcategory> implements SubcategoryService {
}
