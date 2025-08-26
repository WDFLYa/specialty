package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;

    private String detail; // 详细地址

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Integer isDeleted;
}
