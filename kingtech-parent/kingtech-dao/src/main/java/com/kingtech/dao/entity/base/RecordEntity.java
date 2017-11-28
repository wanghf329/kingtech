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

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import com.kingtech.enums.PushStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class RecordEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	
	
	// 创建时间
	@Column(name="CREATE_TIME")
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
}
