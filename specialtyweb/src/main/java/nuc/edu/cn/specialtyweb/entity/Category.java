package nuc.edu.cn.specialtyweb.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Long id;
    private String name;
    private String description;
}
