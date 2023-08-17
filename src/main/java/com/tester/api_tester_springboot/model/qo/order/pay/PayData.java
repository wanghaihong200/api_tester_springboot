package com.tester.api_tester_springboot.model.qo.order.pay;

import lombok.Data;

import java.util.List;

@Data
public class PayData {
	private String amount;
	private List<CardsItem> cards;
	private String dataSource;
	private String paymentType;
}