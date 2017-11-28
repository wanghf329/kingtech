package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.dao.entity.enums.EmployeeStatus;

/**
 * 机构基本信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_BRANCH")
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends UuidEntity {
	
	// 公司名称
	@Column(name="CORPORATE_NAME")
	private String corporateName;

	// 法人
	@Column(name="LEGAL_REPRESENTATIVE")
	private String legalRepresentative;

	// 注册资本
	@Column(name="REG_DAPITAL")
	private Double regDapital;

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
		
	// 创建时间
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	// 修改时间
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
}
