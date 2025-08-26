package nuc.edu.cn.specialtyweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nuc.edu.cn.specialtyweb.entity.Address;
import nuc.edu.cn.specialtyweb.entity.Cart;
import nuc.edu.cn.specialtyweb.entity.CartItem;
import nuc.edu.cn.specialtyweb.entity.Specialty;
import nuc.edu.cn.specialtyweb.service.CartItemService;
import nuc.edu.cn.specialtyweb.service.CartService;
import nuc.edu.cn.specialtyweb.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@Tag(name = "购物车接口", description = "用于管理购物车")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping("/add")
    @Operation(summary = "添加特产到购物车")
    public String addItemToCart(@RequestParam long userId,
                                @RequestParam int specialtyId,
                                @RequestParam int quantity) {

        LambdaQueryWrapper<Specialty> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Specialty::getId, specialtyId);
        Specialty specialty = specialtyService.getOne(queryWrapper);
        if (specialty == null) {
            return "特产不存在";
        }

        Double price = specialty.getPrice();

        try {
            boolean success = cartItemService.addItemToCart(userId, specialtyId, quantity, price);
            if (success) {
                return "添加成功";
            } else {
                return "该特产已添加到购物车";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "添加购物车异常：" + e.getMessage();
        }
    }



    @PostMapping("/selectcartidbyuserid")
    @Operation(summary = "根据用户id查找购物车")
    public Long getCartIdByUserId(@RequestParam Long userId) {

        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId);
        Cart cart = cartService.getOne(queryWrapper);
        return cart.getId();
    }

    @GetMapping("/selectcartitemsbycartid")
    @Operation(summary = "根据购物车ID查找购物车中的所有商品")
    public List<CartItem> getCartItemsByCartId(@RequestParam Long cartId) {
        LambdaQueryWrapper<CartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartItem::getCartId,cartId);
        return cartItemService.list(queryWrapper);
    }

    @PostMapping("/deletebyids")
    @Operation(summary = "删除购物车中的多个商品")
    public String removeCartItems(@RequestBody List<Long> cartItemIds) {
        try {
            boolean removed = cartItemService.removeBatchByIds(cartItemIds);
            return removed ? "删除成功" : "删除失败";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败：" + e.getMessage();
        }
    }

}
