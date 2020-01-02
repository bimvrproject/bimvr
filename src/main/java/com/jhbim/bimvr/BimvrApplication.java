package com.jhbim.bimvr;

import com.jhbim.bimvr.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@SpringBootApplication(scanBasePackages = "com.jhbim.bimvr")
@MapperScan("com.jhbim.bimvr.dao.mapper")
@EnableScheduling
public class BimvrApplication extends SpringBootServletInitializer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 设置哪些接口可以跨域访问
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");   // 设置允许哪些来源地址访问
        corsConfiguration.addAllowedHeader("*");   // 设置允许哪些请求头
        corsConfiguration.addAllowedMethod("*");   // 设置允许的方法，Get / Post / Delete / Put 等
        return corsConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(BimvrApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

}
