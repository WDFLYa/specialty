package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@TableName("city_info") // 表名
public class CityInfo {

    private Integer id;
    private String name;

    @TableField("province_id")
    private Integer provinceId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
