package com.qhh.bank;

import com.qhh.bank.config.MybatisDataSourceConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableTransactionManagement
@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
public class DirectbankRest {

    public static void main(String[] args) {
        SpringApplication.run(DirectbankRest.class,args);
    }
}
