package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnterpriseCustomerModel {

	
	//企业名称
	@JSONField(name="corporate_name")
	private String  corporateName;
	
	//企业规模：1) 个体工商户  2) 农村专业合作组织 3) 微型企业4)小型企业5)中型企业6)大型企业7)其他组织
	
	@JSONField(name="scale")
	private String  scale;
	
	//产业类型：1) 第一产业   2）第二产业  3）第三产业
	
	@JSONField(name="industry_type")
	private  String industryType;
	
	//所属行业：1）居民服务和其他服务业,2）建筑业, 3）交通运输、仓储和邮政业, 4）农、林、牧、渔业, 5）采矿业, 6）制造业, 7）电力、燃气及水的生产和供应业, 8）信息传输、计算机服务和软件业, 9）批发和零售业,10）住宿和餐饮业, 11）房地产业, 12）租赁和商务服务业, 13）其他
	
	@JSONField(name="industry_involved")
	private String  industryinvolved;
		
	//组织机构代码
	@JSONField(name="organization_code")
	private String  organizationcode;
		
	//公司注册登记号
	@JSONField(name="reg_code")
	private String  regcode;
		
	//工商注册登记机关
	@JSONField(name="reg_office")
	private String  regoffice;
		
	//工商注册登记日期，格式YYYY-MM-DD
	@JSONField(name="reg_date")
	private String  regDate;
		
	//国税税务登记号
	@JSONField(name="national_reg_num")
	private String  nationalregNum;
		
	//地税税务登记号
	@JSONField(name="land_reg_num")
	private String  landregNum;
		
	//营业执照号
	@JSONField(name="licence")
	private String  licence;
		
	//营业执照截止日期，格式YYYY-MM-DD
	@JSONField(name="licence_end_date")
	private String  licenceEndDate;
		
	//企业性质
	@JSONField(name="nature")
	private String  nature;
		
	//从业人数
	@JSONField(name="employ_num")
	private String  employNum;
		
	//法定代表人
	@JSONField(name="legal_representative")
	private String  legalRepresentative;
		
	//成立日期，格式YYYY-MM-DD
	@JSONField(name="bulid_date")
	private String  bulidDate;
		
	//实际控制人
	@JSONField(name="actual_controller")
	private String  actualController;
		
	//注册资本
	@JSONField(name="reg_capital")
	private String  regCapital;
		
	//实收资本
	@JSONField(name="really_capital")
	private String  reallyCapital;
		
	//经营范围
	@JSONField(name="business_scope")
	private String  businessScope;
		
	//注册地址
	@JSONField(name="reg_address")
	private String  regAddress;
		
	//通讯地址（省份）
	@JSONField(name="contact_address_province")
	private String  contactAddressProvince;
		
	//通讯地址（市）
	@JSONField(name="contact_address_city")
	private String  contactAddresscity;
		
	//通讯地址（区/县）
	@JSONField(name="contact_address_district")
	private String  contactAddressDistrict;
		
	//通讯地址（详细）
	@JSONField(name="contact_address")
	private String  contactAddress;
		
	//邮政编码
	@JSONField(name="contact_address")
	private String  postcode;
		
	//联系电话
	@JSONField(name="phone")
	private String  phone;
		
	//联系人
	@JSONField(name="linkman")
	private String  linkman;
		
	//传真
	@JSONField(name="fax")
	private String  fax;
		
	//公司邮箱
	@JSONField(name="email")
	private String  email;
		
	//网址
	@JSONField(name="web_site")
	private String  webSite;




}
