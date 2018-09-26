package com.kingtech.model;

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
public class PersonalCustomerModel extends BaseRequestModel {
	
	private String id;
	
	//姓名
	@JSONField(name="name", label="sign")
	private String name;

	//性别：1）男 2）女
	private SexEnum sex;
	
	//证件类型
	private CardTypeEnum cardType;
	
	// 证件号码
	private String cardNumber;
	
	// 联系电话
	private String phone;
	
	// 是农牧民（0：否，1：是）
	private YesNoEnum isFarmer;
	
	//学历（1：博士，2：硕士，3：本科，4：专科，5：高中及以下）
	private EducationEnum education;
	
	// 电子邮件
	private String email;
	
	//婚姻状况，1：已婚，0：未婚
	private YesNoEnum isMarry;
	
	//国籍
	private String nationality;
	
	//出生日期，格式YYYY-MM-DD
	private Date birthDate;
	
	//民族
	private String nation;
	
	// 固定住址（详细）
	private String address;
	
	//部门
	@JSONField(name="department")
	private String department;

	
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
	@Column(name="quitTime")
	private String quitTime;
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public PersonalCustomerModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId);
	}

	//邮政编码
	private String postCode;
	
	// 户口所在地
	private String registeredAddress;
	
	// 籍贯
	private String nativePlace;
	
	// 现工作单位
	private String workUnit;
}
