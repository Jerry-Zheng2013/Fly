package com.ticketsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.ticketsystem.dao")
@EnableAsync
public class TicketSystemApplication {
	
	static Logger log = LogManager.getLogger(TicketSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
        log.info("================");
    }
}

