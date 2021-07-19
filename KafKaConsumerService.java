
package com.rahul.kafka.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rahul.kafka.model.ValidatedConsumerClass;
import com.rahul.kafka.service.DBService;

@Service
public class KafKaConsumerService
{
	private final Logger logger = LoggerFactory.getLogger(KafKaConsumerService.class);
	

	private final ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Autowired
	private DBService dbservice;

	@KafkaListener(id = "foo", topics = "mytopic-3", concurrency = "3", groupId = "mytopic-1-groupid")
	public void consumeFromTopic1(@Payload @Valid ValidatedConsumerClass message, ConsumerRecordMetadata c)  {

		dbservice.processInDB(message);
		
	
			executorService.submit(() -> dbservice.processInDB(message)); 
		
		   


	}

}
