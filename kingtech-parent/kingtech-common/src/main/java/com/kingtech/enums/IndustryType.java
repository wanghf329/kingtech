package com.kingtech.enums;

public enum IndustryType implements BaseEnum{
	S_1("第一产业"),
	S_2("第二产业"),
	S_3("第三产业");
	

	private String key;
	
	private IndustryType(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

}
