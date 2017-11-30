package com.kingtech.enums;

public enum CollateralTypeFor1Enum implements BaseEnum {
	S_1("存货抵押"),
	S_2("客账抵押"),
	S_3("证券抵押"),
	S_4("设备抵押"),
	S_5("不动产抵押"),
	S_6("人寿险抵押"),
	S_7("其他");

	private String key;
	
	private CollateralTypeFor1Enum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
