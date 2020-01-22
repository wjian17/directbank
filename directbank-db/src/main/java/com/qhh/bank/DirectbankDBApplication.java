package com.qhh.bank;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@EnableConfigurationProperties
@EnableTransactionManagement
public class DirectbankDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectbankDBApplication.class,args);
    }
}
