package com.rahul.kafka.model;

import java.beans.ConstructorProperties;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bar {
	
	
	  
	  @JsonCreator
      public Bar(@JsonProperty(value="bid",required = true) String bid,@JsonProperty(value="bname",required = true)  String bname) {
		this.bid = bid;
		this.bname = bname;
	}

	@NotNull
    @Length(min = 1, max = 2)
     private String bid;
	
	@JsonProperty(value="bname")
	private String bname;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
	
	
}
