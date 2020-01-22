package com.qhh.bank.service;

import com.qhh.bank.mapper.master.Test1Mapper;
import com.qhh.bank.mapper.salver.Test2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Test1Service {

    @Autowired
    @Qualifier("test1Mapper")
    private Test1Mapper test1Mapper;

    @Autowired
    @Qualifier("test2Mapper")
    private Test2Mapper test2Mapper;


    public void test(){
        String str = test1Mapper.queryById();
        System.out.println(str);
        str = test2Mapper.queryById();
        System.out.println(str);
    }
}
