package com.tester.api_tester_springboot.model.qo.platform;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnifyNameAddItem {
	// 三级菜单
	private String menuThree;
	// 名单类型[普通名单-0 黑名单-1 灰名单-2 白名单-3]
	private String nameType;
	// 是否生效
	private int valid=1;
	// 备注
	private String note="测试";
	// 一级菜单
	private String menuOne;
	// 是否缓存
	private int cache = 1;
	private List<BatchAddNameValueDTOListItem> batchAddNameValueDTOList;
	// 过期时间, 当前时间+1小时
	private String expirationTime=DateUtil.format(new Date(DateUtil.current()+3600*1000), DatePattern.NORM_DATETIME_PATTERN);
	// 操作时间
	private String operateTime= DateUtil.now();
	// 二级菜单
	private String menuTwo;
	// 操作人
	private String operator="tester-1207663";
}