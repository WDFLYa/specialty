package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.Category;
import nuc.edu.cn.specialtyweb.mapper.CategoryMapper;
import nuc.edu.cn.specialtyweb.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
