package com.kingtech.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestModel implements Serializable {
	/**
	 * 非现场监管系统分配给接入方的账号
	 */
	@JSONField(name="client_id",label="sign")
	private String clientId;
	/**
	 * 随机数
	 */
	@JSONField(name="round_str",label="sign")
	private String roundStr;
	/**
	 * 非现场监管系统分配给接入方的key值
	 */
	@JSONField(name="app_key")
	private String appKey;
	/**
	 * 生成的token值
	 */
	@JSONField(name="token",label="sign")
	private String token;
	/**
	 * A新增 U更新 D删除
	 */
	@JSONField(name="identifier",label="sign")
	private String identifier;
	/**
	 * 外部系统记录id
	 */
	@JSONField(name="req_id",label="sign")
	private String reqId;
	
	@JSONField(name="sign")
	private String sign;
	
	private String id;

	public BaseRequestModel(String id) {
		this.id = id;
	}
}
