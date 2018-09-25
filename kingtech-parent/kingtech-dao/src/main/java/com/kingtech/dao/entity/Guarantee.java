package com.kingtech.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;
import com.kingtech.enums.CardTypeEnum;

/**
 * 保证人信息表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_CONTRACT_GUARANTEE")
@NoArgsConstructor
public class Guarantee extends PackageEntity {
	
	// 保证人名字
	@Column(name="NAME")
	private String name;

	//证件类型
	@Enumerated(EnumType.ORDINAL)
	@Column(name="CARD_TYPE",nullable = false)
	private CardTypeEnum cardType;
	
	// 证件号码
	@Column(name="CARD_NUMBER",nullable = false)
	private String cardNumber;

	// 联系地址
	@Column(name="ADDRESS")
	private String address;
}
