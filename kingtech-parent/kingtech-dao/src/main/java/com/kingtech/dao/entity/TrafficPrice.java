package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.kingtech.dao.entity.base.BaseEntity;
import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.SpecificationsEnum;

/**
 * 流量单价
 * @author: JKWHF
 * @date: 2016年1月28日 下午12:56:42
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_TRAFFICE_PRICE")
@AllArgsConstructor
public class TrafficPrice extends UuidEntity {
	// 流量规格
	private SpecificationsEnum specifications;

	// 流量单价
	private Integer price;

	// 有效期
	private Date timeValidStart;

	// 有效期
	private Date timeValidEnd;

	// 是否启用
	private Boolean enabled;
}
