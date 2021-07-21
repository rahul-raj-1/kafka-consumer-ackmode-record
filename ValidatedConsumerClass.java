package com.rahul.kafka.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatedConsumerClass {

	@NotNull
	@Max(value=10)
	@JsonProperty("bar")
	private String bar;

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

}