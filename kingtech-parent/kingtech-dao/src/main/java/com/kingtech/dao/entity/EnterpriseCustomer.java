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

import com.kingtech.dao.entity.base.PackageEntity;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;

@Data
@Entity
@Table(name="TB_ENTERPRISE_CUSTOMER")
@NoArgsConstructor
public class EnterpriseCustomer extends PackageEntity{
	
	//企业名称
	@Column(name="CORPORATE_NAME",nullable = false)
	private String  corporateName;
	
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
	private IndustryEnum  industryinvolved;
		
	//组织机构代码
	@Column(name="ORGANIZATION_CODE")
	private String  organizationcode;
		
	//公司注册登记号
	@Column(name="REG_CODE")
	private String  regcode;
		
	//工商注册登记机关
	@Column(name="REG_OFFICE")
	private String  regoffice;
		
	//工商注册登记日期，格式YYYY-MM-DD
	@Column(name="REG_DATE")
	private Date  regDate;
		
	//国税税务登记号
	@Column(name="NATIONAL_REG_NUM")
	private String  nationalregNum;
		
	//地税税务登记号
	@Column(name="LAND_REG_NUM")
	private String  landregNum;
		
	//营业执照号
	@Column(name="LICENCE",nullable = false)
	private String  licence;
		
	//营业执照截止日期，格式YYYY-MM-DD
	@Column(name="LICENCE_END_DATE",nullable = false)
	private String  licenceEndDate;
		
	//企业性质
	@Column(name="NATURE")
	private String  nature;
		
	//从业人数
	@Column(name="EMPLOY_NUM")
	private int  employNum;
		
	//法定代表人
	@Column(name="LEGAL_REPRESENTATIVE")
	private String  legalRepresentative;
		
	//成立日期，格式YYYY-MM-DD
	@Column(name="BULID_DATE")
	private Date  bulidDate;
		
	//实际控制人
	@Column(name="ACTUAL_CONTROLLER")
	private String  actualController;
		
	//注册资本
	@Column(name="REG_CAPITAL")
	private BigDecimal  regCapital;
		
	//实收资本
	@Column(name="REALLY_CAPITAL")
	private BigDecimal  reallyCapital;
		
	//经营范围
	@Column(name="BUSINESS_SCOPE")
	private String  businessScope;
		
	//注册地址
	@Column(name="REG_ADDRESS")
	private String  regAddress;
		
	//通讯地址（省份）
	@Column(name="CONTACT_ADDRESS_PROVINCE",nullable = false)
	private String  contactAddressProvince;
		
	//通讯地址（市）
	@Column(name="CONTACT_ADDRESS_CITY",nullable = false)
	private String  contactAddresscity;
		
	//通讯地址（区/县）
	@Column(name="CONTACT_ADDRESS_DISTRICT",nullable = false)
	private String  contactAddressDistrict;
		
	//通讯地址（详细）
	@Column(name="CONTACT_ADDRESS")
	private String  contactAddress;
		
	//邮政编码
	@Column(name="POST_CODE")
	private String  postcode;
		
	//联系电话
	@Column(name="PHONE")
	private String  phone;
		
	//联系人
	@Column(name="LINKMAN")
	private String  linkman;
		
	//传真
	@Column(name="FAX")
	private String  fax;
		
	//公司邮箱
	@Column(name="EMAIL")
	private String  email;
		
	//网址
	@Column(name="WEB_SITE")
	private String  webSite;

	public EnterpriseCustomer(String loanContractId, String corporateName,
			ScaleType scale, IndustryType industryType,
			IndustryEnum industryinvolved, String licence,
			String licenceEndDate, String contactAddressProvince,
			String contactAddresscity, String contactAddressDistrict) {
		super(loanContractId);
		this.corporateName = corporateName;
		this.scale = scale;
		this.industryType = industryType;
		this.industryinvolved = industryinvolved;
		this.licence = licence;
		this.licenceEndDate = licenceEndDate;
		this.contactAddressProvince = contactAddressProvince;
		this.contactAddresscity = contactAddresscity;
		this.contactAddressDistrict = contactAddressDistrict;
	}

}
