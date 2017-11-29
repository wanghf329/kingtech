package com.kingtech.enums;

public enum YesNoEnum implements BaseEnum {
	S_0("否"),
	S_1("是");

	private String key;
	
	private YesNoEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
