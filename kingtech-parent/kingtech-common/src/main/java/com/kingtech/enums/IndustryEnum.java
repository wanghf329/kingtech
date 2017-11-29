package com.kingtech.enums;

public enum IndustryEnum implements BaseEnum {
	S_1("居民服务和其他服务业"),
	S_2("建筑业"),
	S_3("交通运输、仓储和邮政业"),
	S_4("农、林、牧、渔业"),
	S_5("采矿业"),
	S_6("制造业"),
	S_7("电力、燃气及水的生产和供应业"),
	S_8("信息传输、计算机服务和软件业"),
	S_9("批发和零售业"),
	S_10("住宿和餐饮业"),
	S_11("房地产业"),
	S_12("租赁和商务服务业"),
	S_13("其他");

	private String key;
	
	private IndustryEnum(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
