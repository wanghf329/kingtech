package com.kingtech.enums;

public enum BorrowerTypeEnum implements BaseEnum {
	PERSION("0"),//自然人
	CORPORATION("1");//企业

	private String key;
	
	private BorrowerTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	public static BorrowerTypeEnum getValue(String key){
		switch (key) {
		case "0":
			return BorrowerTypeEnum.PERSION;
		case "1":
			return BorrowerTypeEnum.CORPORATION;
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
