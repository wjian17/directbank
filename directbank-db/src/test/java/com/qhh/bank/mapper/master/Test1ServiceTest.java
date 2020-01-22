package com.qhh.bank.mapper.master;

import com.qhh.bank.DirectbankDBApplication;
import com.qhh.bank.service.Test1Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DirectbankDBApplication.class)
public class Test1ServiceTest {

    @Autowired
    private Test1Service test1Service;

    @Test
    public void testMapper() {
        test1Service.test();
    }

}