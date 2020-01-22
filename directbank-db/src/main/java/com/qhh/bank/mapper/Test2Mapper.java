package com.qhh.bank.mapper;

import com.qhh.bank.mapper.salver.SalverMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Test2Mapper extends SalverMapper {

    String queryById();
}
