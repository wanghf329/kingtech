package com.kingtech.dao.entity;

import java.util.Date;

import lombok.Data;

import com.kingtech.dao.entity.base.BaseEntity;
import com.kingtech.enums.SpecificationsEnum;

/**
 * 流量单价
 * @author: JKWHF
 * @date: 2016年1月28日 下午12:56:42
 * @version: v1.0
 */
@Data
public class TrafficPrive extends BaseEntity {
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

	public TrafficPrive(SpecificationsEnum specifications, Integer price, Date timeValidStart, Date timeValidEnd,
			Boolean enabled) {
		super();
		this.specifications = specifications;
		this.price = price;
		this.timeValidStart = timeValidStart;
		this.timeValidEnd = timeValidEnd;
		this.enabled = enabled;
	}
}
