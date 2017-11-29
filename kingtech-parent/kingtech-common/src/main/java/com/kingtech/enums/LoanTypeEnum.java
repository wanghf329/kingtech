package com.kingtech.enums;

public enum LoanTypeEnum  implements BaseEnum{
	
	S_1("信用"),
	S_2("保证"),
	S_3("抵押"),
	S_4("质押"),
	S_5("其他");

	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private LoanTypeEnum(String key) {
		this.key = key;
	}

}
