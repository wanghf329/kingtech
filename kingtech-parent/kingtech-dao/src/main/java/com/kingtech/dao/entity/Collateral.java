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
import com.kingtech.enums.CollateralTypeFor1Enum;
import com.kingtech.enums.PledgeTypeEnum;

/**
 * 抵质押物表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_COLLATERAL")
@NoArgsConstructor
public class Collateral extends PackageEntity {
	
	// 担保类型 1-抵押 2-质押
	@Enumerated(EnumType.STRING)
	@Column(name="PLEDGE_TYPE")
	private PledgeTypeEnum pledgeType;

	// 抵质押物类型：抵押选项：1-存货抵押, 2-客账抵押, 3-证券抵押, 4-设备抵押, 5-不动产抵押, 6-人寿险抵押, 7-其他 
    // 质押选项：1-股权质押, 2-定期存单质押, 3-专利权质押,4-应收账款质押,5-其他
	@Enumerated(EnumType.STRING)
	@Column(name="COLLATERAL_TYPE")
	private CollateralTypeFor1Enum collateralType;

	// 抵质押物名称
	@Column(name="COLLATERAL_NAME")
	private String collateralName;

	// 权证号
	@Column(name="WARRANT_NUM")
	private String warrantNum;

	// 评估值(元)
	@Column(name="EVALUATION_VALUE")
	private BigDecimal evaluationValue;
	
	// 权证所有人
	@Column(name="WARRANT_HOLDER")
	private String warrantHolder;
	
	// 抵质押物放置位置
	@Column(name="COLLATERAL_ADDR")
	private String collateralAddr;
	
	// 办理抵质押日期，格式YYYY-MM-DD
	@Column(name="HANDLE_DATE")
	private Date handleDate;

	public Collateral(String loanContractId, PledgeTypeEnum pledgeType,
			CollateralTypeFor1Enum collateralType, String collateralName, String warrantNum,
			BigDecimal evaluationValue, String warrantHolder,
			String collateralAddr, Date handleDate) {
		super(loanContractId);
		this.pledgeType = pledgeType;
		this.collateralType = collateralType;
		this.collateralName = collateralName;
		this.warrantNum = warrantNum;
		this.evaluationValue = evaluationValue;
		this.warrantHolder = warrantHolder;
		this.collateralAddr = collateralAddr;
		this.handleDate = handleDate;
	}
	
}
