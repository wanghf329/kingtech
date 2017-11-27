package com.kingtech.model;


import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 机构基本信息
 * @author baijt
 *
 */

@Data
public class InstitutionInfoModel extends BaseModel {
	
	/**
	 * 公司名称
	 */
	@JSONField(name="corporate_name",label="sign")
	private String corporateName;
	/**
	 * 法人
	 */
	@JSONField(name="legal_representative",label="sign")
	private String legalRepresentative;
	/**
	 * 注册资本（参与签名，数字类型，单位为元）
	 */
	@JSONField(name="reg_capital",label="sign")
	private String regCapital;
	/**
	 * 成立日期，格式YYYY-MM-DD
	 */
	@JSONField(name="build_date")
	private String buildDate;
	/**
	 * 开业日期，格式YYYY-MM-DD
	 */
	@JSONField(name="opening_date")
	private String openingDate;
	/**
	 * 经营地址
	 */
	@JSONField(name="businessAddr")
	private String business_addr;
	/**
	 * 场所面积
	 */
	@JSONField(name="siteArea")
	private String site_area;
	/**
	 * 组织机构代码
	 */
	@JSONField(name="organization_code",label="sign")
	private String organizationCode;
	/**
	 * 营业执照
	 */
	@JSONField(name="licence",label="sign")
	private String licence;
	/**
	 * 国税编码
	 */
	@JSONField(name="national_reg_num",label="sign")
	private String nationalRegNum;
	/**
	 *地税编码
	 */
	@JSONField(name="land_reg_num",label="sign")
	private String landRegNum;
	/**
	 * 经营范围
	 */
	@JSONField(name="business_scope")
	private String businessScope;
	/**
	 * 创建时间，如2016-01-01 11:12:30
	 */
	@JSONField(name="createTime")
	private String create_time;
	/**
	 * 修改时间，如2016-01-01 11:12:30
	 */
	@JSONField(name="update_time")
	private String updateTime;
	
	
	
	public InstitutionInfoModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign, String corporateName, String legalRepresentative,
			String regCapital, String buildDate, String openingDate,
			String business_addr, String site_area, String organizationCode,
			String licence, String nationalRegNum, String landRegNum,
			String businessScope, String create_time, String updateTime) {
		super(clientId, roundStr, appKey, token, identifier, reqId, sign);
		this.corporateName = corporateName;
		this.legalRepresentative = legalRepresentative;
		this.regCapital = regCapital;
		this.buildDate = buildDate;
		this.openingDate = openingDate;
		this.business_addr = business_addr;
		this.site_area = site_area;
		this.organizationCode = organizationCode;
		this.licence = licence;
		this.nationalRegNum = nationalRegNum;
		this.landRegNum = landRegNum;
		this.businessScope = businessScope;
		this.create_time = create_time;
		this.updateTime = updateTime;
	}



	public InstitutionInfoModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String sign) {
		super(clientId, roundStr, appKey, token, identifier, reqId, sign);
	}



	public InstitutionInfoModel() {
	}
	
	
	
	
	
	




}