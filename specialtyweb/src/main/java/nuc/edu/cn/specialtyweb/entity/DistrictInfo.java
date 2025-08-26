package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("district_info")
public class DistrictInfo {

    private Integer id;

    private String name;

    @TableField("city_id")
    private Integer cityId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
