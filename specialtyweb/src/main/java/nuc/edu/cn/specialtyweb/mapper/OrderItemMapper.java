package nuc.edu.cn.specialtyweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.edu.cn.specialtyweb.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Select("SELECT oi.*, s.name AS specialty_name FROM order_items oi LEFT JOIN specialty s ON oi.specialty_id = s.id WHERE oi.order_id = #{orderId}")
    List<OrderItem> selectItemsWithNameByOrderId(Long orderId);
}
