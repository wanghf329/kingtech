package com.kingtech.enums;

public enum CardTypeEnum implements BaseEnum {
	S_1("二代身份证"),
	S_2("港澳通行证"),
	S_3("台湾通行证"),
	S_4("护照");

	private String key;
	
	private CardTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
