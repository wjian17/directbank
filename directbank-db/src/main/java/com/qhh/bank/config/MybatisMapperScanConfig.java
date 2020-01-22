package com.qhh.bank.config;

import com.qhh.bank.mapper.master.MasterMapper;
import com.qhh.bank.mapper.salver.SalverMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisMapperScanConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisMapperScanConfig.class);

    @Value("${mybatis.master.basePackage}")
    private String masterBasePackage;

    @Value("${mybatis.salver.basePackage}")
    private String salverBasePackage;

    @Bean(name="masterMapperScannerConfigurer")
    @ConditionalOnBean(name = "masterSqlSessionTemplate" )
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        logger.info("masterMapperScannerConfigurer加载完毕！");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage(masterBasePackage);
        readMapperScannerConfigurer.setMarkerInterface(MasterMapper.class);
        readMapperScannerConfigurer.setSqlSessionTemplateBeanName("masterSqlSessionTemplate");
        return readMapperScannerConfigurer;
    }

    @Bean(name="SalverMapperScannerConfigurer")
    @ConditionalOnBean(name = "salverSqlSessionTemplate" )
    public MapperScannerConfigurer SalverMapperScannerConfigurer() {
        logger.info("SalverMapperScannerConfigurer加载完毕！");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("salverSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage(salverBasePackage);
        readMapperScannerConfigurer.setMarkerInterface(SalverMapper.class);
        readMapperScannerConfigurer.setSqlSessionTemplateBeanName("salverSqlSessionTemplate");
        return readMapperScannerConfigurer;
    }
}
