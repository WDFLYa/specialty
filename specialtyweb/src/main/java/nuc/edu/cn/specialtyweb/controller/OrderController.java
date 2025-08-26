package nuc.edu.cn.specialtyweb.controller;

import nuc.edu.cn.specialtyweb.entity.Order;
import nuc.edu.cn.specialtyweb.entity.OrderItem;
import nuc.edu.cn.specialtyweb.entity.OrderRequest;
import nuc.edu.cn.specialtyweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order order = new Order();
            order.setUserId(request.getUserId());
            order.setAddressId(request.getAddressId());
            order.setTotalPrice(request.getTotalPrice());
            order.setCreateTime(LocalDateTime.now());  // 加上时间

            List<OrderItem> items = request.getItems();
            orderService.createOrder(order, items);
            return ResponseEntity.ok("订单创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("服务器错误：" + e.getMessage());
        }
    }


    @GetMapping("/list")
    public List<Order> list(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam Long id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok("删除成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("删除失败：" + e.getMessage());
        }
    }

}
