package com.tester.api_tester_springboot.model.qo.platform;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.List;

@Data
public class UnifyNameEdit{
	private String menuThree;
	private String note="测试";
	private String menuOne;
	private String nameValue;
	private int cache=1;
	private String creator="王海虹-1207663";
	private boolean isEyeOpen=false;
	private Object nameDimensionExtend=null;
	private Object nameValueExtend=null;
	private String operateTime= DateUtil.now();
	private Object extendTwo=null;
	private String menuTwo;
	private List<DimensionAndValueItem> dimensionAndValue;
	private String operator="王海虹-1207663";
	private int nameType;
	private int valid;
	private boolean encryption=false;
	private String createTime;
	private String expirationTime;
	private String nameDimension;
	private Object extendOne=null;
	private String id;
	private List<String> menuValue;
}