package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.mapper.SpecialtyMapper;
import nuc.edu.cn.specialtyweb.entity.Specialty;

import nuc.edu.cn.specialtyweb.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements SpecialtyService {
    @Autowired
    private SpecialtyMapper specialtyMapper;

    @Override
    public List<Specialty> getBySubcategoryId(Long subcategoryId) {
        QueryWrapper<Specialty> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subcategory_id", subcategoryId);
        return specialtyMapper.selectList(queryWrapper);
    }
}