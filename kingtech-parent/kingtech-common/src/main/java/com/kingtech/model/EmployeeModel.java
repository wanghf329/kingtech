package com.kingtech.model;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.EmployeeStatus;

/**
 * 员工信息表
 * @author wangyu
 * @version: v1.0
 */
@Data
public class EmployeeModel extends BaseModel {
	
	// 机构名称
	@JSONField(name="name",label="sign")
	private String name;

	// 登录名
	@JSONField(name="phone",label="sign")
	private String loginName;

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
	private Date replyTime;
		
	// 入职时间
	@JSONField(name="entry_time")
	private Date entryTime;
	
	// 是否在职，0-离职，1-在职
	@JSONField(name="status")
	private EmployeeStatus status;
	
	// 离职时间
	@JSONField(name="quit_time")
	private Date quitTime;
	
	// 创建时间
	@JSONField(name="create_time")
	private Date createTime;
	
	// 修改时间
	@JSONField(name="update_time")
	private Date updateTime;

	public EmployeeModel(String clientId, String roundStr, String appKey,
			String token, String identifier, String reqId, String sign,
			String name, String loginName, String phone, String email,
			String postalAddress, String department, String sex,
			String idNumber, String education, int executiveFlag, String post,
			Date replyTime, Date entryTime, EmployeeStatus status,
			Date quitTime, Date createTime, Date updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign);
		this.name = name;
		this.loginName = loginName;
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
	
	public EmployeeModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign) {
		super(clientId, roundStr, appKey, token, identifier, reqId, sign);
	}
	
}
