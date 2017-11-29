package com.kingtech.enums;

public enum PeriodTypeEnum implements BaseEnum {
	S_1("月"),
	S_2("日"),
	S_3("周"),
	S_4("季度"),
	S_5("年");

	private String key;
	
	private PeriodTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
