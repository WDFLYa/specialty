package nuc.edu.cn.specialtyweb.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Subcategory implements Serializable {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
}
