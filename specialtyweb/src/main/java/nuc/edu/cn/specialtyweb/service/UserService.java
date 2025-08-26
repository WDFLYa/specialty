package nuc.edu.cn.specialtyweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.edu.cn.specialtyweb.entity.User;

import java.math.BigDecimal;

public interface UserService extends IService<User> {
    User getByUsername(String username);
    public BigDecimal getUserBalance(Long userId);
}
