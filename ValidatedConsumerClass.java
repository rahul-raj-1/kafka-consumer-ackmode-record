package com.rahul.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@ValidFooBar
public class ValidatedConsumerClass {

	
	@JsonProperty(value="foo")
	private String foo;
	
	@JsonProperty(value="bar")
	private String bar;
	

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
	
	

}
