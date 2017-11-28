/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingtech.dao.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Persistent;

import com.kingtech.enums.PushStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@NoArgsConstructor
public abstract class RecordEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	
	
	// 创建时间
	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	// 修改时间
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
	// reqId
	@Column(name="REQ_ID")
	private String reqId;
	
	// 推送状态
	@Column(name="PUSH_STATUS")
	private PushStatus pushStatus;
	
	@PrePersist
	public void setCreateTime(){
		this.createTime = new Date();
	}

	public RecordEntity(String reqId, PushStatus pushStatus) {
		super();
		this.reqId = reqId;
		this.pushStatus = pushStatus;
	}
}