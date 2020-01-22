package com.qhh.bank.mapper.salver;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Test2Mapper extends SalverMapper {

    String queryById();
}
