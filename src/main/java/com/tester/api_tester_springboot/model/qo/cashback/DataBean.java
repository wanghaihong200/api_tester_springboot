package com.tester.api_tester_springboot.model.qo.cashback;

import lombok.Data;

@Data
public class DataBean{
	private String amount;
	private String guid;
	private String registeredPhone;
	private String type;
	private String dataSource;
}