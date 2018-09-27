package com.kingtech.enums;

/**
 * @author Tson
 * 账户类型
 */
public enum AccountTypeEnum implements BaseEnum {
	S_1("基本户"),
	S_2("一般户"),
	S_3("监管专用户");

	private String key;
	
	private AccountTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

}
