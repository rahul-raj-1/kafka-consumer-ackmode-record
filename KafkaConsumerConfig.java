

package com.rahul.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.rahul.kafka.model.ValidatedConsumerClass;
import com.rahul.kafka.service.DBService;

@Configuration

@EnableKafka
public class KafkaConsumerConfig implements KafkaListenerConfigurer {

	@Autowired
	private LocalValidatorFactoryBean validator;

	@Autowired
	private DBService dbService;

	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	@Override
	public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
		registrar.setValidator(this.validator);

	}

	@Bean
    public SeekToCurrentErrorHandler seekToCurrentErrorHandler() {
        SeekToCurrentErrorHandler seekToCurrentErrorHandler = new SeekToCurrentErrorHandler((record, e) -> {
            System.out.println("RECORD from topic " +record.topic()+
                    " at partition "+record.partition()+" at offset "+record.offset()+
                    " did not process correctly due to a "+ e.getCause());
        }, new FixedBackOff(500L, 3L));
        seekToCurrentErrorHandler.addNotRetryableExceptions(ArithmeticException.class,ArithmeticException.class);
        return seekToCurrentErrorHandler;
    }
	@Bean public ConsumerFactory<String, ValidatedConsumerClass>
  consumerFactory() {
  
  
  ErrorHandlingDeserializer<ValidatedConsumerClass> errorHandlingDeserializer;
  errorHandlingDeserializer = new ErrorHandlingDeserializer<>(new
  JsonDeserializer<>(ValidatedConsumerClass.class));
  
  Map<String, Object> props = new HashMap<>();
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "grpid-mytopic120112141");
  props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
  props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 3);
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
  props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
  
  
  
  
  // return message in JSON format
  return new DefaultKafkaConsumerFactory<>(
  props, new StringDeserializer(), errorHandlingDeserializer);
  
  
  }

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ValidatedConsumerClass>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ValidatedConsumerClass> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(AckMode.RECORD);
		factory.setErrorHandler(seekToCurrentErrorHandler());
		return factory;
	}

}
