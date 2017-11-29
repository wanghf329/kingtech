package com.kingtech.model;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponsModel {
	
	/**
	 * 非现场监管系统分配给接入方的账号
	 */
	@JSONField(label="sign")
	private String clientId;
	/**
	 * 随机数
	 */
	@JSONField(label="sign")
	private String roundStr;
	/**
	 * 非现场监管系统分配给接入方的key值
	 */
	private String appKey;

	/**
	 * 外部系统数据id
	 */
	@JSONField(label="sign")
	private String reqId;

	/**
	 * 返回的code
	 */
	@JSONField(label="sign")
	private String resultCode;
	/**
	 * 返回消息
	 */
	private String resultMsg;
	
	private String sign;
	
	
	@JSONField(serialize = false)
	public boolean isSuccess(){
		return "0000".equals(this.resultCode);
	}

}
