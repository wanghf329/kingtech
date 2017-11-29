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
	
	public static EmployeeStatus getValue(String value) {
		switch(value) {
		case "0":
			return EmployeeStatus.NUM0;
		case "1":
			return EmployeeStatus.NUM1;
		}
		return null;
	}
	
}
