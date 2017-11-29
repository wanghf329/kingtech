/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingtech.dao.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@NoArgsConstructor
public abstract class PackageEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	
	// 合同编号
	@Column(name="LOAN_CONTRACT_ID")
	private String loanContractId;
	
	public PackageEntity(String loanContractId) {
		super();
		this.loanContractId = loanContractId;
	}
}
