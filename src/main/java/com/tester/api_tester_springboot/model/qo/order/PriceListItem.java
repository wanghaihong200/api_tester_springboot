package com.tester.api_tester_springboot.model.qo.order;

import lombok.Data;

@Data
public class PriceListItem{
	private double salePriceOriginal;
	private double realCostOriginal;
	private double realCost;
	private int id;
	private String stayday;
	private int roomId;
	private double realSalePrice;
}