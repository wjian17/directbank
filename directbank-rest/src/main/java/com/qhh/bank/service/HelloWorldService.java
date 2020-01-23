package com.qhh.bank.service;

import com.qhh.bank.domain.pojo.HelloWorldPojo;
import com.qhh.bank.mapper.HelloWorldPojoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    @Qualifier("helloWorldPojoMapper")
    private HelloWorldPojoMapper helloWorldPojoMapper;

    public HelloWorldPojo test(String flowNo){
        HelloWorldPojo helloWorldPojo = helloWorldPojoMapper.selectByPrimaryKey(flowNo);
        System.out.println(helloWorldPojo);
        return helloWorldPojo;
    }
}
