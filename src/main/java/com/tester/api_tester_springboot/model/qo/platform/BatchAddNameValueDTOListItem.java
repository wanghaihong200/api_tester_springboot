package com.tester.api_tester_springboot.model.qo.platform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchAddNameValueDTOListItem{
	private String nameValue;
	private String nameDimension;
	private String nameValueExtend;
	private String nameDimensionExtend;
}