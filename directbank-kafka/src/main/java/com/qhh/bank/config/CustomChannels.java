package com.qhh.bank.config;

import org.springframework.messaging.SubscribableChannel;
public interface CustomChannels {

//	@Input("inputOrg")
	SubscribableChannel inputOrg();
}