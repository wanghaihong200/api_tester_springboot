package com.tester.api_tester_springboot.model.qo.platform;

import lombok.Data;

import java.util.List;

@Data
public class BusinessFeaturesItem{
	private String createTime;
	private String businessLine;
	private List<FactorsItem> factors;
}