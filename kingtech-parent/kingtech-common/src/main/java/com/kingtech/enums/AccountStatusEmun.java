package com.kingtech.enums;

/**
 * @author Tson
 * 账户状态 
 */
public enum AccountStatusEmun implements BaseEnum {
	normal("正常"),
	loss("挂失"),
	lock("锁定"),
	pledge("质押"),
	cancel("销户"),
	other("其它");
	

	private String key;
	
	private AccountStatusEmun(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}


}
