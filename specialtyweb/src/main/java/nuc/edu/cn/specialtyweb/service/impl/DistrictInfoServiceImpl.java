package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.DistrictInfo;
import nuc.edu.cn.specialtyweb.mapper.DistrictInfoMapper;
import nuc.edu.cn.specialtyweb.service.DistrictInfoService;
import org.springframework.stereotype.Service;

@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo> implements DistrictInfoService {
}
