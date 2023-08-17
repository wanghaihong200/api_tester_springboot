package com.tester.api_tester_springboot.model.qo.platform;

import lombok.Data;

@Data
public class AugurResultResponse {
	private String msg;
	private int code;
	private AugurResultData data;
}