package com.kingtech.enums;

public enum RepayStatusEnum  implements BaseEnum{
	
	S_0("未还"),
	S_1("已还");

	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private RepayStatusEnum(String key) {
		this.key = key;
	}

}
