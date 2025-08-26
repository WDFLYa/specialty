package nuc.edu.cn.specialtyweb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.security.Timestamp;

@Data
public class ProvinceInfo {

    private Long id;

    private String name;

    private Timestamp createTime;

    private Timestamp updateTime;

}
