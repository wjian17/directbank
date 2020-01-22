package com.qhh.bank.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(MybatisDataSourceConfig.class)
public class MybatisSqsSessionFactory {

    private static Logger logger = LoggerFactory.getLogger(MybatisDataSourceConfig.class);

    @Resource
    private DataSource masterDataSource;

    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory getMasterSqlSessionFactroy() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(masterDataSource);
        return sqlSessionFactory.getObject();
    }

    @Resource
    private DataSource salverDataSource;

    @Bean("salverSqlSessionFactory")
    public SqlSessionFactory getSalverSqlSessionFactroy() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(salverDataSource);
        return sqlSessionFactory.getObject();
    }
}
