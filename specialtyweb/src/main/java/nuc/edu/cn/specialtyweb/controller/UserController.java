package nuc.edu.cn.specialtyweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nuc.edu.cn.specialtyweb.entity.Cart;
import nuc.edu.cn.specialtyweb.entity.User;
import nuc.edu.cn.specialtyweb.service.CartService;
import nuc.edu.cn.specialtyweb.service.UserService;
import nuc.edu.cn.specialtyweb.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "用户管理接口", description = "用于管理用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    // 注册接口
    @PostMapping("/register")
    @Operation(summary = "用户注册")

    public String register(@RequestBody User user) {
        // MD5 加密密码
        String rawPassword = user.getPassword();
        String encryptedPassword = md5Encrypt(rawPassword);
        user.setPassword(encryptedPassword);

        // 初始化余额
        user.setBalance(BigDecimal.ZERO);

        // 保存用户
        boolean success = userService.save(user);
        Long userId = user.getId();

        // 4. 创建对应购物车记录
        Cart cart = new Cart();
        cart.setUserId(userId);
        cartService.save(cart);
        return success ? "注册成功" : "注册失败";
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Map<String, Object> login(@RequestBody User loginUser) {
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, loginUser.getUsername())
                .eq(User::getPassword, md5Encrypt(loginUser.getPassword()))
        );

        if (user != null) {
            String token = JwtUtils.generateToken(user.getId());
            Map<String, Object> res = new HashMap<>();
            res.put("code", 200);
            res.put("message", "登录成功");
            res.put("token", token);
            res.put("id", user.getId());           // ✅ 添加 userId
            res.put("username", user.getUsername()); // ✅ 可选，返回用户名也很有用
            return res;
    } else {
            return Map.of("code", 401, "message", "用户名或密码错误");
        }
    }


    // 工具：MD5 加密
    private String md5Encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 如果有服务端登出逻辑（比如清理token黑名单等）这里写

        // 这里直接返回成功状态，前端收到后删除本地token即可
        return ResponseEntity.ok("退出登录成功");
    }

    @GetMapping("/current")
    @Operation(summary = "获取当前登录用户信息")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("code", 401, "message", "无效的Token"));
            }
            String token = authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
            Long userId = JwtUtils.getUserIdFromToken(token);
            if (userId == null) {
                return ResponseEntity.status(401).body(Map.of("code", 401, "message", "无效的Token"));
            }

            User user = userService.getById(userId);
            if (user == null) {
                return ResponseEntity.status(404).body(Map.of("code", 404, "message", "用户不存在"));
            }

            Map<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("username", user.getUsername());
            data.put("balance", user.getBalance());

            return ResponseEntity.ok(Map.of("code", 200, "message", "success", "data", data));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("code", 500, "message", "服务器错误"));
        }
    }
    // 充值接口
    @PostMapping("/recharge")
    @Operation(summary = "用户充值接口，根据用户ID增加余额")
    public Map<String, Object> recharge(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        Map<String, Object> response = new HashMap<>();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            response.put("code", 400);
            response.put("message", "充值金额必须大于0");
            return response;
        }

        User user = userService.getById(userId);
        if (user == null) {
            response.put("code", 404);
            response.put("message", "用户不存在");
            return response;
        }

        // 余额累加
        BigDecimal newBalance = user.getBalance().add(amount);
        user.setBalance(newBalance);
        userService.updateById(user);

        response.put("code", 200);
        response.put("message", "充值成功");
        response.put("balance", newBalance);

        return response;
    }

    @GetMapping("/getBalance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long userId) {
        BigDecimal balance = userService.getUserBalance(userId);
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(balance);
    }

}
