package com.kingtech.enums;

public enum BadTypeEnum implements BaseEnum {
	S_1("普通坏账"),
	S_2("逾期转坏账");

	private String key;
	
	private BadTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
