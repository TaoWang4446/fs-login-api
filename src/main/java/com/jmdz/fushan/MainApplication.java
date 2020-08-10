package com.jmdz.fushan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * MainApplication
 *
 * @author LiCongLu
 * @date 2020-05-29 11:04
 */
@SpringBootApplication
@MapperScan( basePackages = "com.jmdz.fushan.dao")
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources( MainApplication.class );
    }
}
