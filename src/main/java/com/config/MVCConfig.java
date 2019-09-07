package com.config;

import com.component.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 该类为配置类
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("index");
        registry.addViewController("/helloLogin").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/shop.html").setViewName("shop");
        registry.addViewController("/cart").setViewName("cart");
        registry.addViewController("/items.html").setViewName("items");
        registry.addViewController("/confirm_order.html").setViewName("confirm_order");
        registry.addViewController("/success.html").setViewName("success");
        registry.addViewController("/fail.html").setViewName("fail");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration  = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/addCart");
        registration.addPathPatterns("/cart");
        registration.addPathPatterns("/confirm_order.html");
        registration.addPathPatterns("/getOrderInfo");
        registration.addPathPatterns("/getAdderss");
        registration.addPathPatterns("/success.html");
//        registration.addPathPatterns("/getUserInfo");

    }
}
