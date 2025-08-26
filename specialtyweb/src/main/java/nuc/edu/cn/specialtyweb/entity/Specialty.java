package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("specialty")
public class Specialty implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Long subcategoryId;
    private String imageUrl;
    private String description;
    private String origin;
    private String story;
    private Date createdAt;
    private Date updatedAt;
    private double price;
}
