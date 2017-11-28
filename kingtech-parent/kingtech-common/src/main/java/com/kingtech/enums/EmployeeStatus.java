package com.kingtech.enums;

public enum EmployeeStatus implements BaseEnum {
	
	NUM0("0"),//离职
	NUM1("1");//在职
	
	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private EmployeeStatus(String key) {
		this.key = key;
	}
	
}
