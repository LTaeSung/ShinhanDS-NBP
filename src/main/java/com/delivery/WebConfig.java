package com.delivery;


import com.delivery.domain.member.intercepter.LogIntercepter;
import com.delivery.domain.member.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String resourcePath = "/upload/**"; // view 에서 접근할 경로
    private String savePath = "file:///C:/springboot_img/"; // 실제 파일 저장 경로(win)
//    private String savePath = "file:///Users/사용자이름/springboot_img/"; // 실제 파일 저장 경로(mac)

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath, "/static/**")
                .addResourceLocations(savePath)
                .addResourceLocations("classpath:/static/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(new LogIntercepter())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/img/**", "/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/mail", "/mailOwner", "/member/email-check", "/member/login", "/member/save", "/member/logout"
                        , "/css/**", "/html/**", "/img/**", "/js/**", "/*.ico", "/error", "/session-info", "/owner/login", "/owner/save", "/owner/logout"

                        , "/owner/email-check", "/ownerSignIn", "/customer/**", "/customer/", "/api/articles/**" , "/static/img/**" , "/images/**", "/static/js/order/cart.js"
                        , "/articles/**", "/articles/new", "/layouts/**", "/api/get-more-data", "/searchResults","/store/**", "/api/**" , "/owner/**");



    }
}