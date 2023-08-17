package com.tester.api_tester_springboot.model.dto;

import lombok.Data;

/**
*
* 会员信息接口返回
* @date: 2023/5/22
*/
@Data
public class MemberInfoResponse{
	private String rspcode;
	private MemberInfoData data;
	private String message;
}