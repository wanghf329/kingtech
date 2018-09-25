package com.kingtech.enums;

/**
 * @author Tson
 * 账户类型
 */
public enum AccountTypeEnum implements BaseEnum {
	S_0("账户类型"),
	S_1("一般户"),
	S_2("监管专用户");

	private String key;
	
	private AccountTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

}
