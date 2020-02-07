package com.qhh.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DirectbankKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectbankKafkaApplication.class, args);
    }


}
