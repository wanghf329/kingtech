package com.kingtech.enums;

/**
 * @author Tson
 * 学历
 */
public enum EducationEnum  implements BaseEnum{
	S_1("博士"),
	S_2("硕士"),
	S_3("本科"),
	S_4("专科"),
	S_5("高中及以下");

	private String key;
	
	@Override
	public String getKey() {
		return key;
	}

	private EducationEnum(String key) {
		this.key = key;
	}
	
}
