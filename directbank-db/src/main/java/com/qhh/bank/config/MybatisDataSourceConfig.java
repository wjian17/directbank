package com.qhh.bank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by home on 2020/1/17.
 */
@Configuration
public class MybatisDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisDataSourceConfig.class);

    @Value("${mybatis.master.basePackage}")
    private String masterBasePackage;

    @Value("${mybatis.salver.basePackage}")
    private String salverBasePackage;

    @Bean(name = "mhikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariConfig mhikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(@Qualifier("mhikariConfig")HikariConfig mhikariConfig) {
        logger.debug("主数据源加载完毕！");
        setHikariconfigProperties(mhikariConfig);
        return new HikariDataSource(mhikariConfig);
    }

    @Bean(name = "shikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.salver")
    public HikariConfig shikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "salverDataSource")
    public DataSource slaveDataSource(@Qualifier("shikariConfig")HikariConfig shikariConfig) {
        logger.debug("从数据源加载完毕！");
        setHikariconfigProperties(shikariConfig);
        return new HikariDataSource(shikariConfig);
    }

    public HikariConfig setHikariconfigProperties(HikariConfig hikariConfig) {
        hikariConfig.setMaximumPoolSize(50);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setConnectionTimeout(34000);
        hikariConfig.setIdleTimeout(28740000);
        hikariConfig.setMaxLifetime(28740000);
        hikariConfig.setAutoCommit(false);
        return hikariConfig;
    }



//    @Bean(name = "masterSqlSessionTemplate")
//    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory masterSqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(masterSqlSessionFactory);
//    }
//
//    @Bean(name = "salverSqlSessionTemplate")
//    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("salverSqlSessionFactory") SqlSessionFactory salverSqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(salverSqlSessionFactory);
//    }

}
