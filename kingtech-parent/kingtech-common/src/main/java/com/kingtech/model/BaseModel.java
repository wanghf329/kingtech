package com.kingtech.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel implements Serializable {
	/**
	 * 非现场监管系统分配给接入方的账号
	 */
	@JSONField(name="clientId",label="sign")
	private String clientId;
	/**
	 * 随机数
	 */
	@JSONField(name="roundStr",label="sign")
	private String roundStr;
	/**
	 * 非现场监管系统分配给接入方的key值
	 */
	@JSONField(name="appKey")
	private String appKey;
	/**
	 * 生成的token值
	 */
	@JSONField(name="token",label="sign")
	private String token;
	/**
	 * 外部系统记录id
	 */
	@JSONField(name="reqId",label="sign")
	private String reqId;
	
	@JSONField(name="sign")
	private String sign;
	
	private String id;

	public BaseModel(String id) {
		this.id = id;
	}

	public BaseModel(String clientId, String roundStr, String appKey,
			String token, String reqId) {
		super();
		this.clientId = clientId;
		this.roundStr = roundStr;
		this.appKey = appKey;
		this.token = token;
		this.reqId = reqId;
	}
	
	public BaseModel(String id,String reqId) {
		this.id = id;
		this.reqId = reqId;
	}
	
}
