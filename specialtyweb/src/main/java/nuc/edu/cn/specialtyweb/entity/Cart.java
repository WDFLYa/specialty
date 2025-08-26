package nuc.edu.cn.specialtyweb.entity;

import lombok.Data;

import java.util.List;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart") // 可选，如果类名和表名不一致就必须加
public class Cart {

    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;

    private Long userId;
}
