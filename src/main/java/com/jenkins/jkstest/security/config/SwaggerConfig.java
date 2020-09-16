package com.jenkins.jkstest.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author l
 * @date 2020/6/23 17:23
 * @description swagger配置
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //通过包筛选
                //.apis(RequestHandlerSelectors.basePackage("com.jenkins.jkstest.*ller"))
                // 通过注解筛选
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    /**
     * @return api介绍
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("jenkins SpringBoot Swagger")
                .description("SpringBoot前后端联调swagger api 文档")
                .version("1.0")
                .contact(new Contact("Ezreal-y", "https://ezreal-y.github.io/", "1027832188@qq.com"))
                .license("The Apache License")
                .licenseUrl("http://www.baidu.com")
                .build();
    }
}
