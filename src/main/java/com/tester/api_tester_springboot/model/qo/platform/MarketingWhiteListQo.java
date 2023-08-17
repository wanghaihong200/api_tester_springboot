package com.tester.api_tester_springboot.model.qo.platform;

import com.tester.api_tester_springboot.model.qo.PageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketingWhiteListQo extends PageDto {
	private String dimension;
	private String value;
	private String operator = "1207663";
}