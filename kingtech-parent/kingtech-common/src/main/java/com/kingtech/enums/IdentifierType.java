package com.kingtech.enums;

public enum IdentifierType  implements BaseEnum{
	
	A("新增"),
	U("更新"),
	D("删除");

	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private IdentifierType(String key) {
		this.key = key;
	}

}
