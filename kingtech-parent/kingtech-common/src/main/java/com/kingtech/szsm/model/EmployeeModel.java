package com.kingtech.szsm.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 机构人员信息
 * @author Tson
 * @version: v1.0
 */
@Data
public class EmployeeModel extends BaseRequestModel {
	
	//姓名
	@JSONField(name="name", label="sign")
	private String name;
	
	// 联系电话
	@JSONField(name="phone")
	private String phone;
	
	// 电子邮件
	@JSONField(name="email")
	private String email;

	// 通讯地址
	@JSONField(name="address")
	private String address;
	
	
	//部门
	@JSONField(name="department")
	private String department;
	
	// 性别
	@JSONField(name="sex",label="sign")
	private int sex;
	
	//证件类型：1）身份证、2）护照、3）驾驶证，（参与签名）
	@JSONField(name="cardType",label="sign")
	private int cardType;
	
	// 身份证号
	@JSONField(name="cardNumber",label="sign")
	private String cardNumber;
		
	// 学历
	
	@JSONField(name="education")
	private int education;
	
	// 是否懂监高，0-否，1-是
	@JSONField(name="executiveFlag",label="sign")
	private int executiveFlag;
	
	// 职务
	@JSONField(name="position",label="sign")
	private String position;
	
		
	// 入职时间
	@JSONField(name="entryTime")
	private String entryTime;
	
	// 离职时间
	@JSONField(name="quitTime",label="sign")
	private String quitTime;
	
	public EmployeeModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign) {
//		super(clientId, roundStr, appKey, token, identifier, reqId, sign,null);
	}

	public EmployeeModel( String roundStr, String reqId,
			String sign, String name, String phone, String email,
			String address, String department, int sex, int cardType,
			String cardNumber, int education, int executiveFlag,
			String position, String entryTime, String quitTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, sign, null);
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.department = department;
		this.sex = sex;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.education = education;
		this.executiveFlag = executiveFlag;
		this.position = position;
		this.entryTime = entryTime;
		this.quitTime = quitTime;
	}
	}
	
	
