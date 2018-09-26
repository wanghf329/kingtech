package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.CardTypeEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 保证人
 * @author baijt
 *
 */
public class GuaranteeRequestModel {
	
	// 保证人名字
	private String name;

	//证件类型
	private int cardType;
	
	// 证件号码
	private String cardNumber;

	// 联系地址
	private String address;
}
