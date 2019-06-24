package com.sugarframework.admin.config.web.mvc;

import com.sugarframework.admin.module.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhu
 * @description: TODO
 * @date 2019-06-14 16:17
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;


    /**
     * 配置静态资源访问
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/static/lib/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }
}
