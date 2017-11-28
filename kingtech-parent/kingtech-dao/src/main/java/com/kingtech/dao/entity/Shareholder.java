package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.SpecificationsEnum;

/**
 * 股东信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_SHAREHOLDER")
@NoArgsConstructor
@AllArgsConstructor
public class Shareholder extends UuidEntity {
	
	// 股东类型
	@Column(name="PARTNER_TYPE")
	private String partnerType;

	// 持股人
	@Column(name="HOLDER")
	private String holder;

	// 持股比例（%）
	@Column(name="HOLDING_SCALE")
	private Double holdingScale;

	// 出资金额（万元）
	@Column(name="CONTRIBUTION_AMOUNT")
	private Double contributionAmount;

	// 入股时间
	@Column(name="JOIN_TIME")
	private Date joinTime;
	
	// 性别
	@Column(name="GENDER")
	private int gender;
	
	// 离职时间
	@Column(name="QUIT_TIME")
	private Date quitTime;
	
	// 创建时间
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	// 修改时间
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
}
