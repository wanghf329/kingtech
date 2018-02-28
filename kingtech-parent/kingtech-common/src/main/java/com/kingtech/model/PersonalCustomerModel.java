package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.enums.CertType;
import com.kingtech.enums.SexEnum;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalCustomerModel {
	
	private String id;

	//姓名
	@JSONField(name="name")
	private String name;

	//性别：1）男 2）女

	@JSONField(name="sex")
	private String sex;
	
	//证件类型：1）居民身份证 2）护照,  3）驾驶证
	
	@JSONField(name="category")
	private String category;
	
	// 证件号码
	@JSONField(name="card_num")
	private String cardNum;
	
	// 联系电话
	@JSONField(name="phone")
	private String phone;
	
	//是否农牧民：1）是   2）否
	
	@JSONField(name="farmers_flag")
	private String farmersFlag;
	
	//学历
	@JSONField(name="education")
	private String education;
	
	// 传真
	@JSONField(name="fax")
	private String fax;
	
	// 电子邮件
	@JSONField(name="email")
	private String email;
	
	//婚姻状况
	@JSONField(name="marriage")
	private String marriage;
	
	//国籍
	@JSONField(name="nationality")
	private String nationality;
	
	//出生日期，格式YYYY-MM-DD
	@JSONField(name="birth_date")
	private String birthDate;
	
	//民族
	@JSONField(name="nation")
	private String nation;
	
	//固定住址（省份）
	@JSONField(name="address_province")
	private String addressProvince;
	
	//固定住址（市）
	@JSONField(name="address_city")
	private String addressCity;
	
	// 固定住址（区/县）
	@JSONField(name="address_district")
	private String addressDistrict;
	
	// 固定住址（详细）
	@JSONField(name="address")
	private String address;
	
	//邮政编码
	@JSONField(name="post_code")
	private String postCode;
	
	// 户口所在地
	@JSONField(name="residence")
	private String residence;
	
	// 籍贯
	@JSONField(name="native_place")
	private String nativePlace;
	
	// 现工作单位
	@JSONField(name="work_unit")
	private String workUnit;
	
	//职务
	@JSONField(name="post")
	private String post;
	
	
	
	public PersonalCustomerModel(String id,String name, String sex, String category,
			String cardNum, String phone, String farmersFlag,
			String addressProvince, String addressCity, String addressDistrict,
			String address) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.category = category;
		this.cardNum = cardNum;
		this.phone = phone;
		this.farmersFlag = farmersFlag;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDistrict = addressDistrict;
		this.address = address;
	}
}
