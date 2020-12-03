package com.example.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
                .ignoredParameterTypes(ModelAndView.class, RedirectAttributes.class,
                        HttpServletRequest.class, HttpServletResponse.class);

        /*docket.ignoredParameterTypes(ModelAndView.class, RedirectAttributes.class,
                HttpServletRequest.class, HttpServletResponse.class);
        https://github.com/springfox/springfox/issues/3380*/

    }
    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Projeto Spring-React Escola",
                "API REST",
                "1.0",
                "Terms of Service",
                new Contact("Thiago Regis", null, "..."),
                "Apache License Version 2.0",
                "https://www.apache.org/license.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}

// http://localhost:8080/v2/api-docs
// http://localhost:8080/swagger-ui/index.html#/
