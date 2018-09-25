package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;

/**
 * 机构人员信息
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_BRANCH_EMPLOYEE")
@NoArgsConstructor
public class Employee extends RecordEntity {
	
	//姓名
	@Column(name="NAME", nullable = false)
	private String name;
	
	// 联系电话
	@Column(name="PHONE", nullable = false)
	private String phone;
	
	// 电子邮件
	@Column(name="EMAIL")
	private String email;
	
	// 固定住址（详细）
	@Column(name="ADDRESS", nullable = false)
	private String address;
	
	//部门
	@Column(name="DEPARTMENT", nullable = false)
	private String department;

	//性别：1）男 2）女
	@Enumerated(EnumType.ORDINAL)
	@Column(name="SEX",nullable = false)
	private SexEnum sex;
	
	//证件类型：1）居民身份证 2）护照,  3）驾驶证
	@Enumerated(EnumType.STRING)
	@Column(name="CARD_TYPE",nullable = false)
	private CertType cardType;
	
	// 证件号码
	@Column(name="CARD_NUMBER", nullable = false)
	private String cardNumber;
	
	//学历
	@Enumerated(EnumType.STRING)
	@Column(name="EDUCATION", nullable = false)
	private EducationEnum education;
	
	//是否董监高，0-否，1-是
	@Column(name="EXECUTIVE_FLAG", nullable = false)
	private YesNoEnum executiveFlag;
	
	//职务
	@Column(name="POSITION", nullable = false)
	private String position;
	
	//入职日期，格式YYYY-MM-DD
	@Column(name="ENTRY_TIME", nullable = false)
	private Date entryTime;
	
	//离职日期，格式YYYY-MM-DD
	@Column(name="QUIT_TIME")
	private Date quitTime;

	public Employee(String name, String phone, String email,
			String address, String department, SexEnum sex, CertType cardType,
			String cardNumber, EducationEnum education,
			YesNoEnum executiveFlag, String position, Date entryTime,
			Date quitTime) {
		super();
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

	public Employee(String name, String phone, String address,
			String department, SexEnum sex, CertType cardType,
			String cardNumber, EducationEnum education,
			YesNoEnum executiveFlag, String position, Date entryTime) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.department = department;
		this.sex = sex;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.education = education;
		this.executiveFlag = executiveFlag;
		this.position = position;
		this.entryTime = entryTime;
	}
	
}
