package com.kingtech.enums;

public enum LoanstatusEnum implements BaseEnum {
	S1("未结清"),
	S2("已结清"),
	S3("逾期未结清"),
	S4("逾期已结清");

	private String key;
	
	private LoanstatusEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
