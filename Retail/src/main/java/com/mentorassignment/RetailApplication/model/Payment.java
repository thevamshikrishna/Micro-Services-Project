package com.mentorassignment.RetailApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment{

	@JsonProperty("amount")
	private Object amount;

	@JsonProperty("method")
	private String method;

	@JsonProperty("orderId")
	private int orderId;

	@JsonProperty("paymentId")
	private int paymentId;

	@JsonProperty("status")
	private String status;
}