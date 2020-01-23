package com.qhh.bank.controller;

import com.qhh.bank.domain.pojo.HelloWorldPojo;
import com.qhh.bank.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService test1Service;

    @GetMapping("/test/{flowNo}")
    public HelloWorldPojo test(@PathVariable String flowNo){
        return test1Service.test(flowNo);
    }

    @PostMapping("/test1")
    public HelloWorldPojo test1(@RequestParam("flowNo") String flowNo){
        return test1Service.test(flowNo);
    }

    @PostMapping("/test2")
    public HelloWorldPojo test1(){
        return test1Service.test("333333");
    }
}
