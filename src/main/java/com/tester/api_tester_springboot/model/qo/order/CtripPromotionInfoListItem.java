package com.tester.api_tester_springboot.model.qo.order;

import lombok.Data;

@Data
public class CtripPromotionInfoListItem{
	private String promotionTagId;
	private String promotionTagName;
	private int promotionType;
	private int isGroupPromotion;
	private double promotionSale;
	private int promotionMethod;
	private double promotionCost;
	private long availableDate;
	private String strategy;
	private int ctripPromotionId;
	private String promotionId;
}