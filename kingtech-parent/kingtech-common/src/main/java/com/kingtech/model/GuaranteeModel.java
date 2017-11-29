package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuaranteeModel {
	
	// 保证人名字
	@JSONField(name="name")
	private String name;

	// 保证人证件号
	@JSONField(name="card_num")
	private String cardNum;

	// 保证人联系方式
	@JSONField(name="phone")
	private String phone;

	// 联系地址
	@JSONField(name="address")
	private String address;


}
