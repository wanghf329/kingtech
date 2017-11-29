package com.kingtech.enums;

public enum LoanPurposeEnum implements BaseEnum {
	S_1("流动资金贷款"),
	S_2("固定资产投资贷款"),
	S_3("其他");

	private String key;
	
	private LoanPurposeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
