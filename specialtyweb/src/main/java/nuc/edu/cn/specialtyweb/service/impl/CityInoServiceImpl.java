package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.CityInfo;
import nuc.edu.cn.specialtyweb.mapper.CityInfoMapper;
import nuc.edu.cn.specialtyweb.service.CityInfoService;
import org.springframework.stereotype.Service;


@Service
public class CityInoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements CityInfoService {
}
