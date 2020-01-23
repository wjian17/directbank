package com.qhh.bank.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Test1Mapper extends MasterMapper {

    String queryById();
}
