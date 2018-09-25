package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.CardTypeEnum;
import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.FarmersFlagEnum;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;


@Data
@Entity
@Table(name="TB_BORROWER_PERSONAL")
@NoArgsConstructor
public class PersonalCustomer extends UuidEntity{
	//姓名
	@Column(name="NAME",nullable = false)
	private String name;

	//性别：1）男 2）女
	@Enumerated(EnumType.ORDINAL)
	@Column(name="SEX",nullable = false)
	private SexEnum sex;
	
	//证件类型
	@Enumerated(EnumType.ORDINAL)
	@Column(name="CARD_TYPE",nullable = false)
	private CardTypeEnum cardType;
	
	// 证件号码
	@Column(name="CARD_NUMBER",nullable = false)
	private String cardNumber;
	
	// 联系电话
	@Column(name="PHONE",nullable = false)
	private String phone;
	
	//是否农牧民：1）是   2）否
	@Enumerated(EnumType.STRING)
	@Column(name="FARMERS_FLAG",nullable = false)
	private FarmersFlagEnum isFarmer;
	
	//学历（1：博士，2：硕士，3：本科，4：专科，5：高中及以下）
	@Enumerated(EnumType.STRING)
	@Column(name="EDUCATION")
	private EducationEnum education;
	
	// 电子邮件
	@Column(name="EMAIL")
	private String email;
	
	//婚姻状况，1：已婚，0：未婚
	@Enumerated(EnumType.STRING)
	@Column(name="MARRIAGE")
	private YesNoEnum isMarry;
	
	//国籍
	@Column(name="NATIONALITY")
	private String nationality;
	
	//出生日期，格式YYYY-MM-DD
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	//民族
	@Column(name="NATION")
	private String nation;
	
	// 固定住址（详细）
	@Column(name="ADDRESS")
	private String address;
	
	//邮政编码
	@Column(name="POST_CODE")
	private String postCode;
	
	// 户口所在地
	@Column(name="REGISTERED_ADDRESS")
	private String registeredAddress;
	
	// 籍贯
	@Column(name="NATIVE_PLACE")
	private String nativePlace;
	
	// 现工作单位
	@Column(name="WORK_UNIT")
	private String workUnit;
	
	//职务
	@Column(name="POSITION")
	private String position;
}
