package nuc.edu.cn.specialtyweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("后台管理系统API")
                        .version("1.0")
                        .description("后台管理系统API"));
    }

    @Bean
    public GroupedOpenApi specialtyApi() {
        return GroupedOpenApi.builder()
                .group("特产管理")
                .pathsToMatch("/specialties/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CategoryApi() {
        return GroupedOpenApi.builder()
                .group("类别管理")
                .pathsToMatch("/categorys/**")
                .build();
    }

    @Bean
    public GroupedOpenApi AddressApi() {
        return GroupedOpenApi.builder()
                .group("地址管理")
                .pathsToMatch("/address/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CartApi() {
        return GroupedOpenApi.builder()
                .group("购物车管理")
                .pathsToMatch("/carts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi UserApi() {
        return GroupedOpenApi.builder()
                .group("用户管理")
                .pathsToMatch("/users/**")
                .build();
    }


    @Bean
    public GroupedOpenApi FileApi() {
        return GroupedOpenApi.builder()
                .group("文件管理")
                .pathsToMatch("/files/**")
                .build();
    }

}
