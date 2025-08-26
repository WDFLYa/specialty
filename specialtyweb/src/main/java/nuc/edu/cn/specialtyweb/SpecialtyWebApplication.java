package nuc.edu.cn.specialtyweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpecialtyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpecialtyWebApplication.class, args);
        System.out.println("🎉 特产电商系统启动成功！");
        System.out.println("🔗 Knife4j 文档访问地址: http://localhost:8080/doc.html");
    }
}