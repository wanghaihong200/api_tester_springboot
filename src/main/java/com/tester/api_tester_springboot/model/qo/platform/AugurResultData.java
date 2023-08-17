package com.tester.api_tester_springboot.model.qo.platform;

import lombok.Data;

import java.util.List;

@Data
public class AugurResultData {
	private String orderId;
	private List<String> rules;
	private List<BusinessFeaturesItem> businessFeatures;
}