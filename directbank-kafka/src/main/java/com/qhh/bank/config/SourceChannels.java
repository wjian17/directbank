package com.qhh.bank.config;

import org.springframework.messaging.MessageChannel;

public interface SourceChannels {
//	@Output("outputOrg")
	MessageChannel outboundOrg();
}