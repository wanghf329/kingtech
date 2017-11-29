package com.kingtech.enums;

public enum CollateralTypeEnum implements BaseEnum {
	S_1("存货抵押/股权质押"),
	S_2("客账抵押/定期存单质押"),
	S_3("证券抵押/专利权质押"),
	S_4("设备抵押/应收账款质押"),
	S_5("不动产抵押/其他"),
	S_6("人寿险抵押"),
	S_7("其他");

	private String key;
	
	private CollateralTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
