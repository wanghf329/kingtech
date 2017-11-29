package com.kingtech.model;

import java.math.BigDecimal;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 机构资本信息表
 * @author wangyu
 * @version: v1.0
 */
@Data
public class CapitalModel extends BaseRequestModel {
	
	// 融资渠道
	@JSONField(name="financing_channel",label="sign")
	private String financingChannel;

	// 融资金额
	@JSONField(name="financing_money",label="sign")
	private String financingMoney;

	// 融资时间
	@JSONField(name="financing_time",label="sign")
	private String financingTime;

	// 到期时间
	@JSONField(name="expiration_time",label="sign")
	private String expirationTime;

	// 实际还款时间
	@JSONField(name="reply_time")
	private String replyTime;
	
	// 创建时间
	@JSONField(name="create_time")
	private String createTime;
	
	// 修改时间
	@JSONField(name="update_time")
	private String updateTime;
	
	
	public CapitalModel( String roundStr,String identifier, String reqId, String sign,
			String financingChannel, String financingMoney, String financingTime,
			String expirationTime, String replyTime, String createTime,
			String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign,null);
		this.financingChannel = financingChannel;
		this.financingMoney = financingMoney;
		this.financingTime = financingTime;
		this.expirationTime = expirationTime;
		this.replyTime = replyTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public CapitalModel(String id,String financingChannel, String financingMoney,
			String financingTime, String expirationTime, String replyTime) {
		super(id);
		this.financingChannel = financingChannel;
		this.financingMoney = financingMoney;
		this.financingTime = financingTime;
		this.expirationTime = expirationTime;
		this.replyTime = replyTime;
	}

	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */

	public CapitalModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId);
	}
	
	
}
