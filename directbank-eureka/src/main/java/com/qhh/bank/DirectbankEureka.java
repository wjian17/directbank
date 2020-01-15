package com.qhh.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DirectbankEureka {

    public static void main(String[] args) {
        SpringApplication.run(DirectbankEureka.class,args);
    }
}
