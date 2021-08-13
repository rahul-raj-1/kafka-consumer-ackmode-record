package com.rahul.kafka.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@ValidFooBar
public class ValidatedConsumerClass {

	@JsonProperty(value = "foo")
	@NotNull
	private Foo foo;

	@JsonProperty(value = "bar")
	@NotNull
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
