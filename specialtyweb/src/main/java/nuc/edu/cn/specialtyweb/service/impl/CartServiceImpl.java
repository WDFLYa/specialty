package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.Cart;
import nuc.edu.cn.specialtyweb.entity.CartItem;
import nuc.edu.cn.specialtyweb.mapper.CartMapper;
import nuc.edu.cn.specialtyweb.service.CartItemService;
import nuc.edu.cn.specialtyweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
