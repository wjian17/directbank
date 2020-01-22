package com.qhh.bank.controller;

import com.qhh.bank.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Test1Service test1Service;

    @PostMapping("/test")
    public void test(){
        test1Service.test();
    }
}
