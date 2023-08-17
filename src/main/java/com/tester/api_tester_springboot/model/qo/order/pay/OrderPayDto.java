package com.tester.api_tester_springboot.model.qo.order.pay;

import lombok.Data;

@Data
public class OrderPayDto {
	private String actionTime;
	private String product;
	private PayData data;
	private String tradeNo;
	private String tianyanCode;
	private String action;
	private int excuted;
	private String relationValue;
	private String source;
	private String serviceName;
	private String relationField;
}