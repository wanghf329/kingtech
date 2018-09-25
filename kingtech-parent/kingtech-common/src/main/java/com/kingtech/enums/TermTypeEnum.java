package com.kingtech.enums;

public enum TermTypeEnum implements BaseEnum {
	S_1("日"),
	S_2("周"),
	S_3("月"),
	S_4("季度"),
	S_5("年");

	private String key;
	
	private TermTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
