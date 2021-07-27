
package com.rahul.kafka.consumer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.Valid;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rahul.kafka.config.ExecutorServiceConfig;
import com.rahul.kafka.model.ValidatedConsumerClass;
import com.rahul.kafka.service.DBService;

@Service
public class KafKaConsumerService extends  AbstractConsumerSeekAware {
	private final Logger logger = LoggerFactory.getLogger(KafKaConsumerService.class);

	private final ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Autowired
	private DBService dbservice;
	
	@Autowired
	private ExecutorServiceConfig executorServiceConfig;

	@KafkaListener(id = "foo", topics = "mytopic-5", concurrency = "5", groupId = "mytopic-1-groupid")
	public void consumeFromTopic1(@Payload @Valid ValidatedConsumerClass message, ConsumerRecordMetadata c) {


		
		CompletableFuture.runAsync(() -> dbservice.processInDB(message),executorServiceConfig.customExecutorService());
		
		System.out.println( "-- Consumer End -- "   + c.partition() + " ---consumer thread-- " + Thread.currentThread().getName());


	}
	
	public void seekToOffset(TopicPartition topicPartition) {
		getSeekCallbackFor(topicPartition);
	}

	
	public void seekToAnOffset(int partition,long offset,String topic) {
		super.getSeekCallbackFor(new TopicPartition(topic,partition)).seek(topic, partition, offset);
	}


}
