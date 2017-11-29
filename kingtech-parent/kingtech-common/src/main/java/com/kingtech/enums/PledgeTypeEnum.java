package com.kingtech.enums;

public enum PledgeTypeEnum implements BaseEnum {
	MORTGAGE("1"),//抵押
	PLEDGE("2");//质押

	private String key;
	
	private PledgeTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	public static PledgeTypeEnum getValue(String key){
		switch (key) {
		case "1":
			return PledgeTypeEnum.MORTGAGE;
		case "2":
			return PledgeTypeEnum.PLEDGE;
		default:
			break;
		}
		return null;
	}
	
	public String getTips(){
		switch (key) {
			case "1":
				return "抵押";
			case "2":
				return "质押";
			default:
				break;
		}
		return null;
	}
}
