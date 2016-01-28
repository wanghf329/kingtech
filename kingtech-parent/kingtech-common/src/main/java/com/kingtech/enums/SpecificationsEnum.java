package com.kingtech.enums;
/**
 * 流量规格
 * 
 * @author: JKWHF
 * @date: 2016年1月28日 下午1:02:00  
 * @version: v1.0
 */
public enum SpecificationsEnum implements  BaseEnum{
	_5M("5M"),
	_10M("5M"),
	_30M("5M"),
	_50M("5M"),
	_100M("5M"),
	_200M("5M"),
	_500M("5M"),
	_1024M("5M");
	
	
	private String key;
	
	private SpecificationsEnum(String key){
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
}
