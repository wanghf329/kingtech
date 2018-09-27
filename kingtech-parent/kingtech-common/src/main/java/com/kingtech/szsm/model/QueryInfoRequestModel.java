package com.kingtech.szsm.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QueryInfoRequestModel extends BaseRequestModel {

	private String api;

	public QueryInfoRequestModel(String reqId,String api) {
		super(null, null, reqId);
		this.api = api;
	}
	
	
	
	
}
