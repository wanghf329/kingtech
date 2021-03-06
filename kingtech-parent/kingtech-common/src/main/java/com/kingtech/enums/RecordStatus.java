package com.kingtech.enums;

public enum RecordStatus implements BaseEnum {
	NORMAL("正常"),	
	DELETEED("删除");
	
	private String key;
	
	private RecordStatus(String key) {
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

}
