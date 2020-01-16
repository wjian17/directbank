package com.qhh.bank;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by home on 2020/1/16.
 */
@Configuration
public class DataSourceLoadConfig {

    @Primary
    @Bean
    public DruidDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.test1, dataSource1);
//
//        targetDataSources.put(DatabaseType.test2, dataSource2);
//
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setD(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(dataSource1 );// 默认的datasource设置为dataSource 1
        return null;
    }


}
