package com.tester.api_tester_springboot.model.dto;

import lombok.Data;

@Data
public class MemberInfoData{
	private String elMemberIdWx;
	private String tcMemberIdApp;
	private String unionId;
	private String tcMemberIdWx;
	private String elMemberIdApp;
}