package com.qhh.bank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Created by home on 2020/1/17.
 */
public class MyBatisDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(MyBatisDataSourceConfig.class);

    private static String dbType;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        dbType = propertyResolver.getProperty("dbType");
    }

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.database.${dbType}.master")
    public DataSource masterDataSource() {
        HikariConfig datasource = new HikariConfig();
        datasource.setMaximumPoolSize(50);
        datasource.setMinimumIdle(10);
        datasource.setConnectionTimeout(34000);
        datasource.setIdleTimeout(28740000);
        datasource.setMaxLifetime(28740000);
        datasource.setAutoCommit(false);
        return new HikariDataSource(datasource);
    }

    @Bean(name = "slaveDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.database.slave")
    public DataSource slaveDataSource() {
        HikariConfig datasource = new HikariConfig();
        datasource.setMaximumPoolSize(50);
        datasource.setMinimumIdle(10);
        datasource.setConnectionTimeout(34000);
        datasource.setIdleTimeout(28740000);
        datasource.setMaxLifetime(28740000);
        datasource.setAutoCommit(false);
        return new HikariDataSource(datasource);
    }

    public static String getDbType() {
        return dbType;
    }
}
