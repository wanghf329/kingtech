package com.kingtech.enums;

public enum CollateralTypeFor2Enum implements BaseEnum {
	S_1("股权质押"),
	S_2("定期存单质押"),
	S_3("专利权质押"),
	S_4("应收账款质押"),
	S_5("其他");

	private String key;
	
	private CollateralTypeFor2Enum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
