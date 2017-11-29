package com.kingtech.enums;

public enum PayTypeEnum implements BaseEnum {
	S_1("等额本息"),
	S_2("等额本金"),
	S_3("先息后本"),
	S_4("灵活还款");

	private String key;
	
	private PayTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
