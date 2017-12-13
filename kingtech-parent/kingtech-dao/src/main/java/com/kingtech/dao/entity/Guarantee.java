package com.kingtech.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;

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

	// 保证人证件号
	@Column(name="CARD_NUM")
	private String cardNum;

	// 保证人联系方式
	@Column(name="PHONE")
	private String phone;

	// 联系地址
	@Column(name="ADDRESS")
	private String address;

	public Guarantee(String loanContractId, String name, String cardNum,
			String phone, String address) {
		super(loanContractId);
		this.name = name;
		this.cardNum = cardNum;
		this.phone = phone;
		this.address = address;
	}
	
}
