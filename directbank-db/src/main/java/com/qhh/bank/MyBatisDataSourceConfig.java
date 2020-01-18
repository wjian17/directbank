package com.qhh.bank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by home on 2020/1/17.
 */
@Configuration
public class MyBatisDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(MyBatisDataSourceConfig.class);

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.database.master")
    public DataSource masterDataSource() {
        return getDataSource();
    }

    @Bean(name = "slaveDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.database.slave")
    public DataSource slaveDataSource() {
        return getDataSource();
    }

    public DataSource getDataSource() {
        HikariConfig datasource = new HikariConfig();
        datasource.setMaximumPoolSize(50);
        datasource.setMinimumIdle(10);
        datasource.setConnectionTimeout(34000);
        datasource.setIdleTimeout(28740000);
        datasource.setMaxLifetime(28740000);
        datasource.setAutoCommit(false);
        return new HikariDataSource(datasource);
    }


    @Primary
    @Bean(name = "ucSeparateTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("ucSeparateDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "ucSeparateSqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("ucSeparateDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:separate/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource dataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND, secondDataSource);
        return new DynamicDataSource(firstDataSource, targetDataSources);
    }

    @Bean(name = "ucSeparateSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ucSeparateSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
