package com.rahul.kafka.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatedConsumerClass {

	@JsonProperty(value = "foo")
	@NotNull
	@Valid
	private Foo foo;

	@JsonProperty(value = "bar")
	@NotNull
	@Valid
	private Bar bar;

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

}
