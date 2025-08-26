package nuc.edu.cn.specialtyweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.edu.cn.specialtyweb.entity.User;
import nuc.edu.cn.specialtyweb.mapper.UserMapper;
import nuc.edu.cn.specialtyweb.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, username);
        return this.getOne(query);
    }

    @Override
    public BigDecimal getUserBalance(Long userId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, userId)
                .select(User::getBalance);  // 只查询余额字段

        User user = this.getOne(queryWrapper);
        if (user != null) {
            return user.getBalance();
        }
        return null;  // 或者抛异常
    }
}
