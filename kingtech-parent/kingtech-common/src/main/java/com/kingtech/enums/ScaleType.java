package com.kingtech.enums;

public enum ScaleType implements BaseEnum {
	
	S_1("个体工商户"),
	S_2("农村专业合作组织"),
	S_3("微型企业"),
	S_4("小型企业"),
	S_5("中型企业"),
	S_6("大型企业"),
	S_7("其他组织");
	
	
	
	private String key;
	
	private ScaleType(String key) {
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}
	

}
