package com.rahul.kafka.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Foo {
	
	


	@JsonProperty(value="fid")
	@NotNull
	private String fid;
	
	@JsonProperty(value="fname")
	@NotNull
	private String fname;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	

}
