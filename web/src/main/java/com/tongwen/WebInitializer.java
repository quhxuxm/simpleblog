package com.tongwen;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableConfigurationProperties
@MapperScan(basePackages = { "com.tongwen.repository.mapper",
        "com.tongwen.domain" })
public class WebInitializer extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory
            .getLogger(WebInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        logger.info("System is running in tomcat container.");
        return application.sources(WebInitializer.class);
    }

    public static void main(String[] args) throws Exception {
        logger.info("System is running as standalone application.");
        SpringApplication.run(WebInitializer.class, args);
    }
}
