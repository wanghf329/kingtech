package com.kingtech.enums;

public enum PledgeTypeEnum implements BaseEnum {
	S_1("抵押"), 
	S_2("质押");

	private String key;

	private PledgeTypeEnum(String key) {
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}
}
