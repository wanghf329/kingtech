package com.kingtech.enums;

public enum PartnerTypeEnum implements BaseEnum {
	PERSION("1"),//自然人
	CORPORATION("2");//法人

	private String key;
	
	private PartnerTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	public static PartnerTypeEnum getValue(String key){
		switch (key) {
		case "1":
			return PartnerTypeEnum.PERSION;
		case "2":
			return PartnerTypeEnum.CORPORATION;
		default:
			break;
		}
		return null;
	}
	
	public String getTips(){
		switch (key) {
			case "1":
				return "自然人";
			case "2":
				return "法人";
			default:
				break;
		}
		return null;
	}
}
