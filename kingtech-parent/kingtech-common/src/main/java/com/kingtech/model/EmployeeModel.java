package com.kingtech.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;

/**
 * 机构人员信息
 * @author Tson
 * @version: v1.0
 */
@Data
@NoArgsConstructor
public class EmployeeModel extends BaseModel {
	
	//姓名
	private String name;
	
	// 联系电话
	private String phone;
	
	// 电子邮件
	private String email;
	
	// 固定住址（详细）
	private String address;
	
	//部门
	private String department;

	//性别：1）男 2）女
	private SexEnum sex;
	
	//证件类型：1）居民身份证 2）护照,  3）驾驶证
	private CertType cardType;
	
	// 证件号码
	private String cardNumber;
	
	//学历
	private EducationEnum education;
	
	//是否董监高，0-否，1-是
	private YesNoEnum executiveFlag;
	
	//职务
	private String position;
	
	//入职日期，格式YYYY-MM-DD
	private Date entryTime;
	
	//离职日期，格式YYYY-MM-DD
	private Date quitTime;
	
	private PushStatus pushStatus;
	
	public EmployeeModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign) {
//		super(clientId, roundStr, appKey, token, identifier, reqId, sign,null);
	}

	public EmployeeModel(String id, String name, String phone, String email,
			String address, String department, SexEnum sex, CertType cardType,
			String cardNumber, EducationEnum education,
			YesNoEnum executiveFlag, String position, Date entryTime,
			Date quitTime, PushStatus pushStatus, String reqId) {
		super(id, reqId);
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
		this.pushStatus = pushStatus;
	}
	
	


	
	
	
	
}
	
	
