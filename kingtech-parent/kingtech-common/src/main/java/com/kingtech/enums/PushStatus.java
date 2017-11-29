package com.kingtech.enums;

public enum PushStatus implements BaseEnum {
	INITATION("初始"),	
	SUCCESS("推送成功"),
	INPROSESS("推送处理中"),
	FAILED("推送失败");
	
	private String key;
	
	private PushStatus(String key) {
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

}
