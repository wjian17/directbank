package com.qhh.bank.mapper;

import com.qhh.bank.domain.pojo.HelloWorldPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HelloWorldPojoMapper extends MasterMapper {
    int deleteByPrimaryKey(String flowNo);

    int insert(HelloWorldPojo record);

    int insertSelective(HelloWorldPojo record);

    HelloWorldPojo selectByPrimaryKey(String flowNo);

    int updateByPrimaryKeySelective(HelloWorldPojo record);

    int updateByPrimaryKey(HelloWorldPojo record);
}