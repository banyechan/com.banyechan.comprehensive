package com.banyechan.comprehensive.banyechan_tomcat_war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BanyechanTomcatWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanyechanTomcatWarApplication.class, args);
    }


}

//public class BanyechanTomcatWarApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(BanyechanTomcatWarApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(BanyechanTomcatWarApplication.class);
//    }
//}

