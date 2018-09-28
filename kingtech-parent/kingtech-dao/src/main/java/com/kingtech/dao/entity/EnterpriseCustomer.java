package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;

@Data
@Entity
@Table(name="TB_BORROWER_ENTERPRISE")
@NoArgsConstructor
public class EnterpriseCustomer extends UuidEntity{
	
	//企业名称
	@Column(name="NAME",nullable = false)
	private String name;
	
	//企业规模：1) 个体工商户  2) 农村专业合作组织 3) 微型企业4)小型企业5)中型企业6)大型企业7)其他组织
	@Enumerated(EnumType.STRING)
	@Column(name="SCALE",nullable = false)
	private ScaleType  scale;
	
	//产业类型：1) 第一产业   2）第二产业  3）第三产业
	@Enumerated(EnumType.STRING)
	@Column(name="INDUSTRY_TYPE",nullable = false)
	private  IndustryType industryType;
	
	//所属行业：1）居民服务和其他服务业,2）建筑业, 3）交通运输、仓储和邮政业, 4）农、林、牧、渔业, 5）采矿业, 6）制造业, 7）电力、燃气及水的生产和供应业, 8）信息传输、计算机服务和软件业, 9）批发和零售业,10）住宿和餐饮业, 11）房地产业, 12）租赁和商务服务业, 13）其他
	@Enumerated(EnumType.STRING)
	@Column(name="INDUSTRY_INVOLVED",nullable = false)
	private IndustryEnum  industryInvolved;
		
	//组织机构代码
	@Column(name="ORGANIZATION_CODE")
	private String  organizationcode;
		
	//公司注册登记号
	@Column(name="REGIST_CODE")
	private String  registCode;
		
	//工商注册登记机关
	@Column(name="REGIST_OFFICE")
	private String  registOffice;
		
	//工商注册登记日期，格式YYYY-MM-DD
	@Column(name="REGIST_DATE")
	private Date  registDate;
		
	//国税税务登记号
	@Column(name="NATIONAL_TAX_CODE")
	private String  nationalTaxCode;
		
	//地税税务登记号
	@Column(name="LAND_TAX_CODE")
	private String  landTaxCode;
		
	//营业执照号
	@Column(name="LICENCE_CODE",nullable = false)
	private String  licenseCode;
		
	//营业执照截止日期，格式YYYY-MM-DD
	@Column(name="LICENCE_END_DATE",nullable = false)
	private Date  licenseEndDate;
		
	//法定代表人
	@Column(name="LEGAL_PERSON")
	private String  legalPerson;
		
	//成立日期，格式YYYY-MM-DD
	@Column(name="FOUND_DATE")
	private Date  foundDate;
		
	//实际控制人
	@Column(name="CONTROLLER")
	private String  controller;
		
	//实收资本
	@Column(name="REALLY_CAPITAL")
	private BigDecimal  reallyCapital;
		
	//经营范围
	@Column(name="BUSINESS_SCOPE")
	private String  businessScope;
		
	//注册地址
	@Column(name="REGISTER_ADDRESS")
	private String  registerAddress;
		
	//通讯地址（详细）
	@Column(name="ADDRESS")
	private String  address;
		
	//联系电话
	@Column(name="PHONE")
	private String phone;
		
	//联系人
	@Column(name="LINKMAN")
	private String linkman;
		
	//公司邮箱
	@Column(name="EMAIL")
	private String email;
		
	//网址
	@Column(name="WEB_SITE")
	private String webSite;
}
