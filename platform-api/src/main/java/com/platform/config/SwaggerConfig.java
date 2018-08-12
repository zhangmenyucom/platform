package com.platform.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author talor
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages="com.platform.api")
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.platform.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(this.apiInfo());
    }


    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("小程序接口文档")
                .description("小程序接口文档")
                .contact(new Contact("Taylor",null,null))
                .version("版本号:1.0")
                .build();
        return apiInfo;
    }
}