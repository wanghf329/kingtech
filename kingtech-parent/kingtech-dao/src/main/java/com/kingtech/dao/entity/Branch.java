package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;

/**
 * 机构基本信息表
 * @version: v1.0
 */

@Data
@Entity
@Table(name="TB_BRANCH")
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends RecordEntity {
	
	// 公司名称
	@Column(name="CORPORATE_NAME")
	private String corporateName;

	// 法人
	@Column(name="LEGAL_REPRESENTATIVE")
	private String legalRepresentative;

	// 注册资本
	@Column(name="REG_DAPITAL")
	private BigDecimal regDapital;

	// 成立日期
	@Column(name="BUILD_DATE")
	private Date buildDate;

	// 开业日期
	@Column(name="OPENING_DATE")
	private Date openingDate;
	
	// 经营地址
	@Column(name="BUSINESS_ADDR")
	private String businessAddr;
	
	// 场所面积
	@Column(name="SITE_AREA")
	private String siteArea;
	
	// 组织机构代码
	@Column(name="ORGANIZATION_CODE")
	private String organizationCode;
		
	// 营业执照
	@Column(name="LICENCE")
	private String licence;
	
	// 国税编码
	@Column(name="NATIONAL_REG_NUM")
	private String nationalRegNum;
	
	// 地税编码
	@Column(name="LAND_REG_NUM")
	private String landRegNum;
	
	// 经营范围
	@Column(name="BUSINESS_SCOPE")
	private String businessScope;

	public Branch(String reqId, PushStatus pushStatus, String corporateName,
			String legalRepresentative, BigDecimal regDapital, Date buildDate,
			Date openingDate, String businessAddr, String siteArea,
			String organizationCode, String licence, String nationalRegNum,
			String landRegNum, String businessScope) {
		super(reqId, pushStatus);
		this.corporateName = corporateName;
		this.legalRepresentative = legalRepresentative;
		this.regDapital = regDapital;
		this.buildDate = buildDate;
		this.openingDate = openingDate;
		this.businessAddr = businessAddr;
		this.siteArea = siteArea;
		this.organizationCode = organizationCode;
		this.licence = licence;
		this.nationalRegNum = nationalRegNum;
		this.landRegNum = landRegNum;
		this.businessScope = businessScope;
	}
	
	
	
}
