package com.qhh.bank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by home on 2020/1/17.
 */
@Configuration
public class MyBatisDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(MyBatisDataSourceConfig.class);

    @Bean(name = "mhikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariConfig mhikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(@Qualifier("mhikariConfig")HikariConfig mhikariConfig) {
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
