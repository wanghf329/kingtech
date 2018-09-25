package com.kingtech.model;

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
	
	// 固定住址（详细）
	@JSONField(name="address")
	private String address;
	
	//部门
	@JSONField(name="department")
	private String department;

	//性别：1）男 2）女
	@JSONField(name="sex", label="sign")
	private String sex;
	
	//证件类型：1）居民身份证 2）护照,  3）驾驶证
	@JSONField(name="cardType", label="sign")
	private String cardType;
	
	// 证件号码
	@JSONField(name="cardNumber", label="sign")
	private String cardNumber;
	
	//学历
	@JSONField(name="education")
	private String education;
	
	//是否董监高，0-否，1-是
	@JSONField(name="executiveFlag", label="sign")
	private String executiveFlag;
	
	//职务
	@JSONField(name="position", label="sign")
	private String position;
	
	//入职日期，格式YYYY-MM-DD
	@JSONField(name="entryTime")
	private String entryTime;
	
	//离职日期，格式YYYY-MM-DD
	@JSONField(name="quitTime")
	private String quitTime;
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public EmployeeModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId);
	}

	public EmployeeModel(String id, String name, String phone, String email,
			String address, String department, String sex, String cardType,
			String cardNumber, String education, String executiveFlag,
			String position, String entryTime, String quitTime) {
		super(id);
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
