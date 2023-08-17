package com.tester.api_tester_springboot.model.qo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestRoom{
	private String mhotelId;
	private String guestroomAmount;
	private String provinceName;
}