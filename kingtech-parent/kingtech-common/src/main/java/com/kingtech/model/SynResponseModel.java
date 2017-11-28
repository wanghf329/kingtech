package com.kingtech.model;


public class SynResponseModel  {
	
	/**
	 * 非现场监管系统分配给接入方的账号
	 */
	private String clientId;
	/**
	 * 随机数
	 */
	
	private String roundStr;
	/**
	 * 非现场监管系统分配给接入方的key值
	 */
	
	private String appKey;

	/**
	 * 外部系统数据id
	 */
	private String reqId;

	/**
	 * 返回的code
	 */
	private String resultCode;
	/**
	 * 返回消息
	 */
	private String resultMsg;
	/**
	 * sign
	 */
	private String sign;

}
