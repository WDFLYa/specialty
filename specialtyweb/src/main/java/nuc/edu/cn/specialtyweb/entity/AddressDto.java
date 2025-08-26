package nuc.edu.cn.specialtyweb.entity;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private Integer userId;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;
    private String detail;

    private String provinceName;
    private String cityName;
    private String districtName;

}