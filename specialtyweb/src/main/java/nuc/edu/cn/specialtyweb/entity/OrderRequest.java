package nuc.edu.cn.specialtyweb.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private Long addressId;
    private BigDecimal totalPrice;
    private List<OrderItem> items;
}
