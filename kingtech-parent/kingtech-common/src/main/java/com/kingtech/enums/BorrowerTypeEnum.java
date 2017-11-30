package com.kingtech.enums;

public enum BorrowerTypeEnum implements BaseEnum {
	S_0("自然人"),
	S_1("企业");

	private String key;
	
	private BorrowerTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
