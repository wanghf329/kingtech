package com.kingtech.enums;

public enum UnionFlagEnum implements BaseEnum {
	S_1("是"),//自然人
	S_2("否");//企业

	private String key;
	
	private UnionFlagEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
