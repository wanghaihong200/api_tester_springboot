package com.tester.api_tester_springboot.model.qo.platform;

import lombok.Data;

import java.util.List;

// 通用名单新增 DTO
@Data
public class UnifyNameAddBatch{
	private List<UnifyNameAddItem> unifyNameAdd;
}