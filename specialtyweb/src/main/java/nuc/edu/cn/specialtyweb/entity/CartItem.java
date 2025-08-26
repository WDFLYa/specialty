package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart_item")  // 确保和你数据库表名一致
public class CartItem {

    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;

    private int cartId;
    private int specialtyId;
    private Integer quantity;
    private double price;
}
