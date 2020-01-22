package com.qhh.bank.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(MybatisDataSourceConfig.class)
public class MybatisSqlSessionFactory {

    private static Logger logger = LoggerFactory.getLogger(MybatisDataSourceConfig.class);

    @Value("${mybatis.config}")
    private String mybatisConfig;

    @Value("${mybatis.master.mapperLocations}")
    private String masterMapperLocations;

    @Value("${mybatis.salver.mapperLocations}")
    private String salverMapperLocations;

    @Resource
    private DataSource masterDataSource;

    @Primary
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory getMasterSqlSessionFactroy() throws Exception {
        logger.debug("masterSqlSessionFactory加载完毕！");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(masterDataSource);
        sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisConfig));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(masterMapperLocations));
        return sqlSessionFactory.getObject();
    }

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

    @Primary
    @Bean("masterSqlSessionTemplate")
    public SqlSessionTemplate getMasterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory masterSqlSessionFactory) throws Exception {
        logger.debug("masterSqlSessionTemplate加载完毕！");
        return new SqlSessionTemplate(masterSqlSessionFactory);
    }

    @Bean("salverSqlSessionTemplate")
    public SqlSessionTemplate getSalverSqlSessionTemplate(@Qualifier("salverSqlSessionFactory") SqlSessionFactory salverSqlSessionFactory) throws Exception {
        logger.debug("salverSqlSessionTemplate加载完毕！");
        return new SqlSessionTemplate(salverSqlSessionFactory);
    }

    @Primary
    @Bean("masterTransactionManager")
    public TransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        logger.debug("masterTransactionManager加载完毕！");
        return new DataSourceTransactionManager(masterDataSource);
    }

    @Bean("salverTransactionManager")
    public TransactionManager salverTransactionManager(@Qualifier("salverDataSource") DataSource salverDataSource) throws Exception {
        logger.debug("salverTransactionManager加载完毕！");
        return new DataSourceTransactionManager(salverDataSource);
    }

}
