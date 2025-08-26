package nuc.edu.cn.specialtyweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nuc.edu.cn.specialtyweb.entity.*;
import nuc.edu.cn.specialtyweb.service.AddressService;
import nuc.edu.cn.specialtyweb.service.CityInfoService;
import nuc.edu.cn.specialtyweb.service.DistrictInfoService;
import nuc.edu.cn.specialtyweb.service.ProvinceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "地址管理接口", description = "用于管理地址")
public class AddressController {
    @Autowired
    private ProvinceInfoService provinceInfoService;

    @Autowired
    private CityInfoService cityInfoService;

    @Autowired
    private DistrictInfoService districtInfoService;
    @Autowired
    private AddressService addressService;


    @GetMapping("/selectprovince")
    @Operation(summary = "查询所有省份")
    public List<ProvinceInfo> getAllProvince() {
        return provinceInfoService.list();
    }

    @GetMapping("/selectcitybyprovinceid")
    @Operation(summary = "根据省份查询城市")
    public List<CityInfo> getCityByProvinceId(@RequestParam int id){
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId,id);
        return cityInfoService.list(queryWrapper);
    }

    @GetMapping("/selectdistrictbycityid")
    @Operation(summary = "根据城市查询区县")
    public List<DistrictInfo> getDistrictByCityId(@RequestParam int id){
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId,id);
        return districtInfoService.list(queryWrapper);
    }


    @PostMapping("/addaddress")
    @Operation(summary = "保存地址")
    public void addAddress(@RequestBody Address address) {
       addressService.save(address);
    }

    @GetMapping("/list")
    @Operation(summary = "根据用户ID查询地址列表，带省市区名称")
    public List<AddressDto> getAddressListByUserId(@RequestParam Integer userId) {
        List<Address> addressList = addressService.list(new LambdaQueryWrapper<Address>()
                .eq(Address::getUserId, userId));

        // 组装带名字的DTO列表
        List<AddressDto> dtoList = addressList.stream().map(addr -> {
            AddressDto dto = new AddressDto();
            dto.setId(addr.getId());
            dto.setUserId(addr.getUserId());
            dto.setProvinceId(addr.getProvinceId());
            dto.setCityId(addr.getCityId());
            dto.setDistrictId(addr.getDistrictId());
            dto.setDetail(addr.getDetail());

            // 查询名字
            dto.setProvinceName(provinceInfoService.getById(addr.getProvinceId()).getName());
            dto.setCityName(cityInfoService.getById(addr.getCityId()).getName());
            dto.setDistrictName(districtInfoService.getById(addr.getDistrictId()).getName());

            return dto;
        }).toList();

        return dtoList;
    }

    @DeleteMapping("/delete")
    @Operation(summary = "根据地址ID删除地址")
    public boolean deleteAddress(@RequestParam Integer id) {
        return addressService.removeById(id);
    }

    @GetMapping("/detail")
    @Operation(summary = "根据地址ID获取完整地址信息")
    public AddressDto getAddressById(@RequestParam Integer id) {
        Address addr = addressService.getById(id);
        if (addr == null) {
            return null;  // 或者抛异常、返回自定义错误
        }

        AddressDto dto = new AddressDto();
        dto.setId(addr.getId());
        dto.setUserId(addr.getUserId());
        dto.setProvinceId(addr.getProvinceId());
        dto.setCityId(addr.getCityId());
        dto.setDistrictId(addr.getDistrictId());
        dto.setDetail(addr.getDetail());

        dto.setProvinceName(provinceInfoService.getById(addr.getProvinceId()).getName());
        dto.setCityName(cityInfoService.getById(addr.getCityId()).getName());
        dto.setDistrictName(districtInfoService.getById(addr.getDistrictId()).getName());

        return dto;
    }

}
