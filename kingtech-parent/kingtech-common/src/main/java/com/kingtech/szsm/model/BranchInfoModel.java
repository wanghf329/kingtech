package com.kingtech.szsm.model;


import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 机构基本信息
 * @author baijt
 *
 */

@Data
public class BranchInfoModel extends BaseRequestModel {
	
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
	@JSONField(name="business_addr")
	private String businessAddr;
	/**
	 * 场所面积
	 */
	@JSONField(name="site_area")
	private String siteArea;
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
	@JSONField(name="create_time")
	private String createTime;
	/**
	 * 修改时间，如2016-01-01 11:12:30
	 */
	@JSONField(name="update_time")
	private String updateTime;
	
	
	
	
	
	
	



	
	
	
	
	




}
