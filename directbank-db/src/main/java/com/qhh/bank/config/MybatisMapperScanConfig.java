package com.qhh.bank.config;

import com.qhh.bank.mapper.MasterMapper;
import com.qhh.bank.mapper.SalverMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
//    @ConditionalOnBean(name = "salverTransactionManager" )
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        logger.info("masterMapperScannerConfigurer加载完毕！");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage("com.qhh.bank.mapper");
        readMapperScannerConfigurer.setMarkerInterface(MasterMapper.class);
        readMapperScannerConfigurer.setSqlSessionTemplateBeanName("masterSqlSessionTemplate");
        return readMapperScannerConfigurer;
    }

    @Bean(name="SalverMapperScannerConfigurer")
//    @ConditionalOnBean(name = "salverTransactionManager" )
    public MapperScannerConfigurer SalverMapperScannerConfigurer() {
        logger.info("SalverMapperScannerConfigurer加载完毕！");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("salverSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage("com.qhh.bank.mapper");
        readMapperScannerConfigurer.setMarkerInterface(SalverMapper.class);
        readMapperScannerConfigurer.setSqlSessionTemplateBeanName("salverSqlSessionTemplate");
        return readMapperScannerConfigurer;
    }
}
