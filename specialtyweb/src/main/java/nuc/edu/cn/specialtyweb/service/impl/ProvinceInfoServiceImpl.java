package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.ProvinceInfo;
import nuc.edu.cn.specialtyweb.mapper.ProvinceInfoMapper;
import nuc.edu.cn.specialtyweb.service.ProvinceInfoService;
import org.springframework.stereotype.Service;

@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo> implements ProvinceInfoService {
}
