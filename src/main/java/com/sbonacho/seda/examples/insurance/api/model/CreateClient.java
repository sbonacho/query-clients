package com.sbonacho.seda.examples.insurance.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClient extends AbstractDto {
    private String name;
    private String address;
    private String interest;

	public String getName() {
        return name;
	}
    public String getAddress() {
        return address;
    }
    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
