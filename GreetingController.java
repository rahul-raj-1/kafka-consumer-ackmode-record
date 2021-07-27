
package com.rahul.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.kafka.consumer.KafKaConsumerService;
import com.rahul.kafka.consumer.ProducerService;
import com.rahul.kafka.model.ValidatedClass;
import com.rahul.kafka.service.GreetingService;

@RestController
public class GreetingController {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private GreetingService greetingService;
	
	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
	
	@Autowired
	private KafKaConsumerService KafKaConsumerService;


	

	ExecutorService executorService = Executors.newFixedThreadPool(3);
	
	
	@GetMapping("/kafka")
	public void pause(  )
	{
		

		//kafkaListenerEndpointRegistry.getListenerContainer("foo").pause();
		
		 KafKaConsumerService.seekToAnOffset(0,5l,"mytopic-3");
		
		//kafkaListenerEndpointRegistry.getListenerContainer("foo").resume();
		
		
	}
	
	@GetMapping("/resume")
	public void resume(  )
	{
		kafkaListenerEndpointRegistry.getListenerContainer("foo").resume();
		System.out.println(" Resume " + kafkaListenerEndpointRegistry.getListenerContainer("foo").isRunning());

		
	}

	@GetMapping("/greet")
	public void greeting() throws Exception {
		
		ValidatedClass val = new ValidatedClass();
		val.setBar("First");
		ValidatedClass val1 = new ValidatedClass();
		val1.setBar("second");
		
		ValidatedClass c= new ValidatedClass();
		c.setBar("Third");
		
		ValidatedClass d= new ValidatedClass();
		d.setBar("Fourth");
		
		ValidatedClass e= new ValidatedClass();
		e.setBar("Fifth");
		
		ValidatedClass f= new ValidatedClass();
		f.setBar("Sixth");
		
		ValidatedClass g= new ValidatedClass();
		g.setBar("Seventh");
		
		ValidatedClass h= new ValidatedClass();
		g.setBar("Eighth");


		
		
		
  ObjectMapper o = new ObjectMapper();

		System.out.println(" ------ Started ----"  + o.writeValueAsString(e) );
		List<ValidatedClass> list = new ArrayList<>();
		list.add(val);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);
		list.add(h);
		list.add(val1);
		

		

		producerService.postMessage(list); // seekOffsetService.seekToBeggining1("1");

	}

}
