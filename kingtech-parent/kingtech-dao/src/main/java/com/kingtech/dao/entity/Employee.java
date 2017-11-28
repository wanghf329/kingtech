package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.EmployeeStatus;

/**
 * 员工信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends UuidEntity {
	
	// 机构名称
	@Column(name="NAME")
	private String name;

	// 登录名
	@Column(name="LOGIN_NAME")
	private String loginName;

	// 联系电话
	@Column(name="PHONE")
	private String phone;

	// 电子邮件
	@Column(name="EMAIL")
	private String email;

	// 通讯地址
	@Column(name="POSTAL_ADDRESS")
	private String postalAddress;
	
	// 部门
	@Column(name="DEPARTMENT")
	private String department;
	
	// 性别
	@Column(name="SEX")
	private String sex;
	
	// 身份证号
	@Column(name="ID_NUMBER")
	private String idNumber;
		
	// 学历
	@Column(name="EDUCATION")
	private String education;
	
	// 是否懂监高，0-否，1-是
	@Column(name="EXECUTIVE_FLAG")
	private int executiveFlag;
	
	// 职务
	@Column(name="POST")
	private String post;
	
	// 批复时间
	@Column(name="REPLY_TIME")
	private Date replyTime;
		
	// 入职时间
	@Column(name="ENTRY_TIME")
	private Date entryTime;
	
	// 是否在职，0-离职，1-在职
	@Column(name="STATUS")
	private EmployeeStatus status;
	
	// 离职时间
	@Column(name="QUIT_TIME")
	private Date quitTime;
	
	// 创建时间
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	// 修改时间
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
}
