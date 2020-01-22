package com.qhh.bank.mapper.master;

import com.qhh.bank.mapper.master.MasterMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Test1Mapper extends MasterMapper {

    String queryById();
}
