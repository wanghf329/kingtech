package com.kingtech.enums;

public enum SexEnum implements BaseEnum {
	M("1"),
	F("2");

	private String key;
	
	private SexEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	public static SexEnum getValue(String key){
		if(key==null){
			return null;
		}
		switch (key) {
			case "1":
				return SexEnum.M;
			case "2":
				return SexEnum.F;
			default:
				break;
		}
		return null;
	}
	
	public String getTips(){
		switch (key) {
		case "1":
			return "男";
		case "2":
			return "女";
		default:
			break;
		}
		return null;
	}
}
