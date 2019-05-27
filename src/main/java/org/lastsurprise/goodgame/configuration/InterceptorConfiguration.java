package org.lastsurprise.goodgame.configuration;

import org.lastsurprise.goodgame.interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

    /**
     * 拦截所有请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public Interceptor interceptor(){
        return new Interceptor();
    }
}
