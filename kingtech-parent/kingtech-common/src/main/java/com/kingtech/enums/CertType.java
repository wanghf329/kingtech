package com.kingtech.enums;

public enum CertType implements BaseEnum{
	
	S_1("居民身份证"),
	S_2("护照"),
	S_3("驾驶证");
	
	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private CertType(String key) {
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
