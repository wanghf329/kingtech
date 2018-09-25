package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.CardTypeEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuaranteeModel {
	
	// 保证人名字
	private String name;

	//证件类型
	private CardTypeEnum cardType;
	
	// 证件号码
	private String cardNumber;

	// 联系地址
	private String address;
}
