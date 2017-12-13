package com.kingtech.enums;

public enum LoanClassificationEnum implements BaseEnum {
	S_1("正常"),
	S_2("关注"),
	S_3("次级"),
	S_4("可疑"),
	S_5("损失");

	private String key;
	
	private LoanClassificationEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
