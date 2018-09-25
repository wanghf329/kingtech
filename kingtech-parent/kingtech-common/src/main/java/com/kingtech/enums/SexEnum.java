package com.kingtech.enums;

public enum SexEnum implements BaseEnum {
	S_1("男"),
	S_2("女");

	private String key;
	
	private SexEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
