package com.qhh.bank.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.qhh.bank.salver",sqlSessionTemplateRef = "salverSqlSessionTemplate")
public class SalverConfig {

    private static Logger logger = LoggerFactory.getLogger(SalverConfig.class);

    @Value("${mybatis.config}")
    private String mybatisConfig;

    @Value("${mybatis.salver.mapperLocations}")
    private String salverMapperLocations;

    @Resource
    private DataSource salverDataSource;

    @Bean("salverSqlSessionFactory")
    public SqlSessionFactory getSalverSqlSessionFactroy() throws Exception {
        logger.debug("salverSqlSessionFactory加载完毕！");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(salverDataSource);
        sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisConfig));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(salverMapperLocations));
        return sqlSessionFactory.getObject();
    }

    @Bean("salverSqlSessionTemplate")
    public SqlSessionTemplate getSalverSqlSessionTemplate(@Qualifier("salverSqlSessionFactory") SqlSessionFactory salverSqlSessionFactory) throws Exception {
        logger.debug("salverSqlSessionTemplate加载完毕！");
        return new SqlSessionTemplate(salverSqlSessionFactory);
    }

    @Bean("salverTransactionManager")
    public TransactionManager salverTransactionManager(@Qualifier("salverDataSource") DataSource salverDataSource) throws Exception {
        logger.debug("salverTransactionManager加载完毕！");
        return new DataSourceTransactionManager(salverDataSource);
    }
}
