package com.tester.api_tester_springboot.model.qo.platform;

import com.tester.api_tester_springboot.model.qo.PageDto;
import lombok.Data;

@Data
public class UnifyNameQuery extends PageDto {
	private String menuThree;
	private String menuOne;
	private String menuTwo;
	private String nameDimension;
	private String nameValue;
	private String decryptionOperator="1207663";

}