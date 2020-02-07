package com.qhh.bank.config;


//@EnableBinding(Processor.class)
public class TransFormService {
 
//    @ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object transform(Object payload){
        System.out.println("消息中转站："+payload);

        return payload;
    }
 
}