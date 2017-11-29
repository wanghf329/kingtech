package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;
import com.kingtech.enums.CertType;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;


@Data
@Entity
@Table(name="TB_PERSONAL_CUSTOMER")
@NoArgsConstructor
public class PersonalCustomer extends PackageEntity{
	//姓名
	@Column(name="NAME",nullable = false)
	private String name;

	//性别：1）男 2）女
	@Enumerated(EnumType.ORDINAL)
	@Column(name="SEX",nullable = false)
	private SexEnum sex;
	
	//证件类型：1）居民身份证 2）护照,  3）驾驶证
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY",nullable = false)
	private CertType category;
	
	// 证件号码
	@Column(name="CARD_NUM",nullable = false)
	private String cardNum;
	
	// 联系电话
	@Column(name="PHONE",nullable = false)
	private String phone;
	
	//是否农牧民：1）是   2）否
	@Enumerated(EnumType.STRING)
	@Column(name="FARMERS_FLAG",nullable = false)
	private YesNoEnum farmersFlag;
	
	//学历
	@Column(name="EDUCATION")
	private String education;
	
	// 传真
	@Column(name="FAX")
	private String fax;
	
	// 电子邮件
	@Column(name="EMAIL")
	private String email;
	
	//婚姻状况
	@Column(name="MARRIAGE")
	private String marriage;
	
	//国籍
	@Column(name="NATIONALITY")
	private String nationality;
	
	//出生日期，格式YYYY-MM-DD
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	//民族
	@Column(name="NATION")
	private String nation;
	
	//固定住址（省份）
	@Column(name="ADDRESS_PROVINCE",nullable = false)
	private String addressProvince;
	
	//固定住址（市）
	@Column(name="ADDRESS_CITY",nullable = false)
	private String addressCity;
	
	// 固定住址（区/县）
	@Column(name="ADDRESS_DISTRICT",nullable = false)
	private String addressDistrict;
	
	// 固定住址（详细）
	@Column(name="ADDRESS")
	private String address;
	
	//邮政编码
	@Column(name="POST_CODE")
	private String postCode;
	
	// 户口所在地
	@Column(name="RESIDENCE")
	private String residence;
	
	// 籍贯
	@Column(name="NATIVE_PLACE")
	private String nativePlace;
	
	// 现工作单位
	@Column(name="WORK_UNIT")
	private String workUnit;
	
	//职务
	@Column(name="POST")
	private String post;

	public PersonalCustomer(String loanContractId, String name, SexEnum sex,
			CertType category, String cardNum, String phone,
			YesNoEnum farmersFlag, String addressProvince, String addressCity,
			String addressDistrict) {
		super(loanContractId);
		this.name = name;
		this.sex = sex;
		this.category = category;
		this.cardNum = cardNum;
		this.phone = phone;
		this.farmersFlag = farmersFlag;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDistrict = addressDistrict;
	}

	
	
}
