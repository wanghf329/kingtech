package com.kingtech.szsm.model;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.CardTypeEnum;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 个人借款人
 * @author baijt
 *
 */
public class PersonalCustomerRequestModel  {
	
	
	//姓名
	private String name;

	//性别：1）男 2）女
	private int sex;
	
	//证件类型
	private int cardType;
	
	// 证件号码
	private String cardNumber;
	
	// 联系电话
	private String phone;
	
	// 是农牧民（0：否，1：是）
	private int isFarmer;
	
	//学历（1：博士，2：硕士，3：本科，4：专科，5：高中及以下）
	private int education;
	
	// 电子邮件
	private String email;
	
	//婚姻状况，1：已婚，0：未婚
	private String isMarry;
	
	//国籍
	private String nationality;
	
	//出生日期，格式YYYY-MM-DD
	private String birthday;
	
	//民族
	private String nation;
	
	// 固定住址（详细）
	private String address;
	
	//邮政编码
	private String postCode;
	
	// 户口所在地
	private String registeredAddress;
	
	// 籍贯
	private String nativePlace;
	
	// 现工作单位
	private String workUnit;
	//职务
	private String position;
}
