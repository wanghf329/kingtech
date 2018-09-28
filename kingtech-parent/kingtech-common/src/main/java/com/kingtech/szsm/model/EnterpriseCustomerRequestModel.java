package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 企业借款人
 * @author baijt
 *
 */
public class EnterpriseCustomerRequestModel {
	
	//企业名称
	private String name;
	
	//企业规模：1) 个体工商户  2) 农村专业合作组织 3) 微型企业4)小型企业5)中型企业6)大型企业7)其他组织
	private int  scale;
	
	//产业类型：1) 第一产业   2）第二产业  3）第三产业
	private  int industryType;
	
	//所属行业：1）居民服务和其他服务业,2）建筑业, 3）交通运输、仓储和邮政业, 4）农、林、牧、渔业, 5）采矿业, 6）制造业, 7）电力、燃气及水的生产和供应业, 8）信息传输、计算机服务和软件业, 9）批发和零售业,10）住宿和餐饮业, 11）房地产业, 12）租赁和商务服务业, 13）其他
	private int  industryInvolved;
		
	//组织机构代码
	private String  organizationcode;
		
	//公司注册登记号
	private String  registCode;
		
	//工商注册登记机关
	private String  registOffice;
		
	//工商注册登记日期，格式YYYY-MM-DD
	private String  registDate;
		
	//国税税务登记号
	private String  nationalTaxCode;
		
	//地税税务登记号
	private String  landTaxCode;
		
	//营业执照号
	private String  licenseCode;
		
	//营业执照截止日期，格式YYYY-MM-DD
	private String  licenseEndDate;
		
	//法定代表人
	private String  legalPerson;
		
	//成立日期，格式YYYY-MM-DD
	private String  foundDate;
		
	//实际控制人
	private String  controller;
		
	//实收资本
	private String  reallyCapital;
		
	//经营范围
	private String  businessScope;
		
	//注册地址
	private String  registerAddress;
		
	//通讯地址（详细）
	private String  address;
		
	//联系电话
	private String phone;
		
	//联系人
	private String linkman;
		
	//公司邮箱
	private String email;
		
	//网址
	private String webSite;
	


	
}
