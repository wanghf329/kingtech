package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异步记过返回
 * @author baijt
 *
 */
@NoArgsConstructor
@Data
public class AsyReponseModel extends BaseResponsModel {
	
	/**
	 * 接口名称：见文档后面2.19 附录：API字典
	 */
	private String api;
	/**
	 * A、U、D
	 */
	private String identifier;
	public AsyReponseModel(String clientId, String roundStr, String appKey,
			String reqId, String resultCode, String resultMsg, String sign,
			String api, String identifier) {
		super(clientId, roundStr, appKey, reqId, resultCode, resultMsg, sign);
		this.api = api;
		this.identifier = identifier;
	}
	
	
	

}
