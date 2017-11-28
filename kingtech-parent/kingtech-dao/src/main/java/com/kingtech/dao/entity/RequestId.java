package com.kingtech.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.BaseEntity;

@Data
@Entity
@Table(name="TB_REQUEST_ID")
@NoArgsConstructor
@AllArgsConstructor
public class RequestId  extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	// 机构名称
    @Column(name="REQUEST_ID")
	private int requestId;

}
