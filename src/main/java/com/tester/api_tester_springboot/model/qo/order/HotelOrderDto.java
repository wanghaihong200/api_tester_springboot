package com.tester.api_tester_springboot.model.qo.order;

import lombok.Data;

@Data
public class HotelOrderDto{
	private String actionTime;
	private String product;
	private DataBean data;
	private String tianyanCode;
	private String action;
	private String callback;
	private int excuted;
	private String relationValue;
	private String source;
	private String serviceName;
	private String relationField;
}