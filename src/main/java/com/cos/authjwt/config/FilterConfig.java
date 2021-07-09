package com.cos.authjwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.authjwt.config.filter.CorsFilter;
import com.cos.authjwt.config.filter.jwt.JwtAuthenticationFilter;
import com.cos.authjwt.config.filter.jwt.JwtAuthorizationFilter;

@Configuration 
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		System.out.println("CORS 필터 등록");
		FilterRegistrationBean<CorsFilter> bean = 
				new FilterRegistrationBean<>(new CorsFilter());
		bean.addUrlPatterns("/*");
		bean.setOrder(0); // 낮은 번호부터 실행됨.
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilter(){
		System.out.println("JwtAuthenticationFilter 등록됨");
		FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>(new JwtAuthenticationFilter());
		bean.addUrlPatterns("/login");
		bean.setOrder(1); // 낮은 번호 부터 실행
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilter(){
		System.out.println("JwtAuthorizationFilter 등록됨");
		FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<>(new JwtAuthorizationFilter());
		bean.addUrlPatterns("/post/*");
		bean.addUrlPatterns("/user/*");
		bean.setOrder(2); // 낮은 번호 부터 실행
		return bean;
	}
	
	
	
}
