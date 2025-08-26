package nuc.edu.cn.specialtyweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.edu.cn.specialtyweb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE user SET balance = balance - #{amount} WHERE id = #{userId} AND balance >= #{amount}")
    int updateBalance(@Param("userId") Long userId, @Param("amount") BigDecimal amount);
}
