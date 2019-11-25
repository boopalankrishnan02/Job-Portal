package com.jb.frontend.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.core.Ordered;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.jb")
public class WebConfig implements WebMvcConfigurer {
		
	@Bean(name ="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
//	@Bean
//	public InternalResourceViewResolver getViewResolver() {
//		InternalResourceViewResolver view = new InternalResourceViewResolver();
//    	view.setPrefix("/WEB-INF/views/");
//		view.setSuffix(".html");
//		return view;
//	}
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index.html");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	}
}
