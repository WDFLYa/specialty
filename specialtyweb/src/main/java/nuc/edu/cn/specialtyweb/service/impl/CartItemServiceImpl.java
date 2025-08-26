package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.Cart;
import nuc.edu.cn.specialtyweb.entity.CartItem;
import nuc.edu.cn.specialtyweb.mapper.CartItemMapper;
import nuc.edu.cn.specialtyweb.service.CartItemService;
import nuc.edu.cn.specialtyweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

    @Autowired
    private CartService cartService; // 获取用户的购物车

    @Override
    public boolean addItemToCart(Long userId, int specialtyId, Integer quantity, Double price) {
        Cart cart = cartService.getOne(new QueryWrapper<Cart>().eq("user_id", userId));
        if (cart == null) {
            throw new RuntimeException("用户购物车不存在");
        }

        QueryWrapper<CartItem> itemQuery = new QueryWrapper<>();
        itemQuery.eq("cart_id", cart.getId())
                .eq("specialty_id", specialtyId);
        CartItem existingItem = this.getOne(itemQuery);

        if (existingItem != null) {
            // 已存在，返回false表示添加失败
            return false;
        } else {
            CartItem item = new CartItem();
            item.setCartId(Math.toIntExact(cart.getId()));
            item.setSpecialtyId(specialtyId);
            item.setQuantity(quantity);
            item.setPrice(price);
            return this.save(item);
        }
    }



}
