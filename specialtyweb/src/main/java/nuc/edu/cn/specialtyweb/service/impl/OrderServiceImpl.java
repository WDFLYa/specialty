package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import nuc.edu.cn.specialtyweb.entity.Order;
import nuc.edu.cn.specialtyweb.entity.OrderItem;
import nuc.edu.cn.specialtyweb.entity.User;
import nuc.edu.cn.specialtyweb.mapper.OrderItemMapper;
import nuc.edu.cn.specialtyweb.mapper.OrderMapper;
import nuc.edu.cn.specialtyweb.mapper.UserMapper;
import nuc.edu.cn.specialtyweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public void createOrder(Order order, List<OrderItem> items) {
        Long userId = order.getUserId();

        // 1. 查询用户余额
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (user.getBalance().compareTo(order.getTotalPrice()) < 0) {
            throw new RuntimeException("余额不足，无法下单");
        }

        // 2. 扣减余额（带条件的更新）
        int result = userMapper.updateBalance(userId, order.getTotalPrice());
        if (result == 0) {
            throw new RuntimeException("扣减余额失败，可能余额不足或并发冲突");
        }

        // 3. 创建订单
        orderMapper.insert(order);

        // 4. 插入订单项
        for (OrderItem item : items) {
            item.setOrderId(order.getId());
            orderItemMapper.insert(item);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = orderMapper.selectByUserId(userId);
        for (Order order : orders) {
            List<OrderItem> items = orderItemMapper.selectItemsWithNameByOrderId(order.getId());
            order.setItems(items);
        }
        return orders;
    }

    @Transactional
    @Override
    public void deleteOrderById(Long orderId) {
        int rows = orderMapper.deleteById(orderId);
        if (rows == 0) {
            throw new RuntimeException("订单不存在，删除失败");
        }
        // order_items 会自动删除，依赖外键约束
    }
}

