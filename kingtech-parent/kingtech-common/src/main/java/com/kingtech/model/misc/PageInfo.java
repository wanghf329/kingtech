package com.kingtech.model.misc;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
public class PageInfo implements Serializable {
	private Integer firstIndex;
	private Integer pageSize;

	public static PageRequest page(Integer firstIndex, Integer pageSize) {
		return new PageRequest(firstIndex/pageSize, pageSize);
	}
}
