package com.kingtech.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 员工信息表
 * @author wangyu
 * @version: v1.0
 */
@Data
public class EmployeeModel extends BaseRequestModel {
	
	// 机构名称
	@JSONField(name="name",label="sign")
	private String name;

	// 联系电话
	@JSONField(name="phone")
	private String phone;

	// 电子邮件
	@JSONField(name="email")
	private String email;

	// 通讯地址
	@JSONField(name="postal_address")
	private String postalAddress;
	
	// 部门
	@JSONField(name="department")
	private String department;
	
	// 性别
	@JSONField(name="sex",label="sign")
	private String sex;
	
	// 身份证号
	@JSONField(name="id_number",label="sign")
	private String idNumber;
		
	// 学历
	@JSONField(name="education")
	private String education;
	
	// 是否懂监高，0-否，1-是
	@JSONField(name="executive_flag",label="sign")
	private int executiveFlag;
	
	// 职务
	@JSONField(name="post",label="sign")
	private String post;
	
	// 批复时间
	@JSONField(name="reply_time")
	private String replyTime;
		
	// 入职时间
	@JSONField(name="entry_time")
	private String entryTime;
	
	// 是否在职，0-离职，1-在职
	@JSONField(name="status")
	private String status;
	
	// 离职时间
	@JSONField(name="quit_time")
	private String quitTime;
	
	// 创建时间
	@JSONField(name="create_time")
	private String createTime;
	
	// 修改时间
	@JSONField(name="update_time")
	private String updateTime;

	public EmployeeModel(String roundStr, String identifier, String reqId, String sign,
			String name, String phone, String email,
			String postalAddress, String department, String sex,
			String idNumber, String education, int executiveFlag, String post,
			String replyTime, String entryTime, String status,
			String quitTime, String createTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign,null);
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.postalAddress = postalAddress;
		this.department = department;
		this.sex = sex;
		this.idNumber = idNumber;
		this.education = education;
		this.executiveFlag = executiveFlag;
		this.post = post;
		this.replyTime = replyTime;
		this.entryTime = entryTime;
		this.status = status;
		this.quitTime = quitTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public EmployeeModel(String id, String name, String phone, String email,
			String postalAddress, String department, String sex,
			String idNumber, String education, int executiveFlag, String post,
			String replyTime, String entryTime, String status,
			String quitTime) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.postalAddress = postalAddress;
		this.department = department;
		this.sex = sex;
		this.idNumber = idNumber;
		this.education = education;
		this.executiveFlag = executiveFlag;
		this.post = post;
		this.replyTime = replyTime;
		this.entryTime = entryTime;
		this.status = status;
		this.quitTime = quitTime;
	}
	
	public EmployeeModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign) {
		super(clientId, roundStr, appKey, token, identifier, reqId, sign,null);
	}
	
}
