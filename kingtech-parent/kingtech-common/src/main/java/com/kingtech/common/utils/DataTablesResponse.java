package com.kingtech.common.utils;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Page;

import com.google.common.collect.Lists;


@Data
@NoArgsConstructor
public class DataTablesResponse {
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<?> data = Lists.newArrayList();
	private DataTablesResponse(Integer draw, Page<?> page) {
		this.draw = draw;
		this.recordsTotal = this.recordsFiltered = page.getTotalElements();
		this.data = page.getContent();
	}
	
	public static DataTablesResponse format(Integer draw, Page<?> page) {
		return new DataTablesResponse(draw, page);
	}

}
