package com.kingtech.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.SpecificationsEnum;

/**
 * 流量单价
 * @author: JKWHF
 * @date: 2016年1月28日 下午12:56:42
 * @version: v1.0
 */
@Data
@SuppressWarnings("serial")
@NoArgsConstructor
public class TrafficPriceModel extends BaseModel {
	
	private String id;
	
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

	public TrafficPriceModel(String id,SpecificationsEnum specifications, Integer price, Date timeValidStart, Date timeValidEnd,
			Boolean enabled) {
		this.id = id;
		this.specifications = specifications;
		this.price = price;
		this.timeValidStart = timeValidStart;
		this.timeValidEnd = timeValidEnd;
		this.enabled = enabled;
	}
}
