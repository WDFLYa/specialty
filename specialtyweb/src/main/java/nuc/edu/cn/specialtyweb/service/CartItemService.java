package nuc.edu.cn.specialtyweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.edu.cn.specialtyweb.entity.CartItem;

public interface CartItemService extends IService<CartItem> {
    boolean addItemToCart(Long userId, int specialtyId, Integer quantity, Double price);
}
