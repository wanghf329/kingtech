package com.kingtech.szsm.model;

import com.kingtech.common.config.BaseConfig;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryInfoRequestModel extends BaseRequestModel {

	private String api;

	public QueryInfoRequestModel(String reqId,String api) {
		super(reqId);
		this.api = api;
	}
	
	
	
	
}
