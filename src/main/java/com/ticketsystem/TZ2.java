package com.ticketsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

public class TZ2 {

    public static void main(String[] args) {
        SpringApplication.run(TZ2.class, args);
        System.out.println("================");
    }
}

/*
public class TicketSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
        System.out.println("================");
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(TicketSystemApplication.class);
    }
    
}
 */

