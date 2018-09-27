/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingtech.dao.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@NoArgsConstructor
public abstract class LoanRecordEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	
	// 合同编号
	@Column(name="LOAN_CONTRACT_ID")
	private String loanContractId;
	
	// 创建时间
	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	// 修改时间
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	// reqId
	@Column(name="REQ_ID")
	private String reqId;
	
	// 推送状态
	@Enumerated(EnumType.STRING)
	@Column(name="PUSH_STATUS")
	private PushStatus pushStatus;
	
	// 删除状态
	@Enumerated(EnumType.STRING)
	@Column(name="RECORD_STATUS")
	private RecordStatus recordStatus;
	
	@PrePersist
	@PreUpdate
	public void setTime(){
		this.createTime = new Date();
		this.updateTime = new Date();
	}
	
	public LoanRecordEntity(String loanContractId, String reqId, PushStatus pushStatus) {
		super();
		this.loanContractId = loanContractId;
		this.reqId = reqId;
		this.pushStatus = pushStatus;
	}
	
}
