package com.ciandt.summit.bootcamp2022.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequiredArgsConstructor
@Component
public class ConfigInterceptor extends WebMvcConfigurerAdapter {

    private final Interceptor inperceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptor)
                .excludePathPatterns("/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**");
    }

}