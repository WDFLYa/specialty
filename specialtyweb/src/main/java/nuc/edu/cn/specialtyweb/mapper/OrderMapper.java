package nuc.edu.cn.specialtyweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.edu.cn.specialtyweb.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> selectByUserId(Long userId);
}
