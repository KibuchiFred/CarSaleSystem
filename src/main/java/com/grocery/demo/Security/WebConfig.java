package com.grocery.demo.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/home/fred/Videos/Uploads/")
                .setCachePeriod(10);

//        WebMvcConfigurer.super.addResourceHandlers(registry);
//
//        System.out.println("Image configuration initialized");
//
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classPath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classPath:/META-INF/resources/webjars/");
//
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
    }
}
