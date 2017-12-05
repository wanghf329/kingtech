package com.kingtech.enums;

public enum FarmersFlagEnum implements BaseEnum {
	S_1("是"),
	S_2("否");

	private String key;
	
	private FarmersFlagEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
