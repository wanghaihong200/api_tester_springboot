package com.tester.api_tester_springboot.model.qo.order;

import lombok.Data;

@Data
public class MemberInfo{
	private String memberLevel;
	private String isMemberAgent;
	private String member;
	private String memberType;
	private String openId;
	private String unionId;

}