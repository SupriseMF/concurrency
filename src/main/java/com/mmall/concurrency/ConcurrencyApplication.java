package com.mmall.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	//使用springboot快速进行测试
	@Bean
	public FilterRegistrationBean httpFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());
		//测试threadLocal
		registrationBean.addUrlPatterns("/threadlocal/*");
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加拦截器，并指定拦截的路径类型
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
