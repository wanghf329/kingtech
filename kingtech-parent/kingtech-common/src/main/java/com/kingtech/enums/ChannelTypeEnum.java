package com.kingtech.enums;

public enum ChannelTypeEnum implements BaseEnum {
	S_1("金融机构融资"),
	S_2("股东借款"),
	S_3("资产转让融资"),
	S_4("其他");

	private String key;
	
	private ChannelTypeEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
