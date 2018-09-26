package com.kingtech.enums;

public enum RateTypeEnum implements BaseEnum {
	S_1("日"),
	S_2("周"),
	S_3("月"),
	S_4("年"),
	S_5("季");

	private String key;
	
	private RateTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
