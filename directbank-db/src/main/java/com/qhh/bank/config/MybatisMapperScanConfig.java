package com.qhh.bank.config;

import com.qhh.bank.mapper.MasterMapper;
import com.qhh.bank.mapper.SalverMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MybatisSqsSessionFactory.class)
public class MybatisMapperScanConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisMapperScanConfig.class);

    @Value("${mybatis.master.basePackage}")
    private String masterBasePackage;

    @Value("${mybatis.salver.basePackage}")
    private String salverBasePackage;

    @Bean(name="masterMapperScannerConfigurer")
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        logger.info("Database Scanner File");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage(masterBasePackage);
        readMapperScannerConfigurer.setMarkerInterface(MasterMapper.class);
        return readMapperScannerConfigurer;
    }

    @Bean(name="SalverMapperScannerConfigurer")
    public MapperScannerConfigurer SalverMapperScannerConfigurer() {
        logger.info("Database Scanner File");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("salverSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage(salverBasePackage);
        readMapperScannerConfigurer.setMarkerInterface(SalverMapper.class);
        return readMapperScannerConfigurer;
    }
}
