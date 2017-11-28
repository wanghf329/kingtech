package com.kingtech.enums;

public enum IdentifierEnum  implements BaseEnum{
	
	A("新增"),
	U("更新"),
	D("删除");

	private String key;

	@Override
	public String getKey() {
		return key;
	}

	private IdentifierEnum(String key) {
		this.key = key;
	}

}
