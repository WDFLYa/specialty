package nuc.edu.cn.specialtyweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.edu.cn.specialtyweb.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
}
