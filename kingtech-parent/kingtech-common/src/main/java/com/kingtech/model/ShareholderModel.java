package com.kingtech.model;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 股东信息表
 * @author wangyu
 * @version: v1.0
 */
@Data
public class ShareholderModel extends BaseModel {
	
	// 股东类型
	@JSONField(name="partner_type",label="sign")
	private String partnerType;

	// 持股人
	@JSONField(name="holder",label="sign")
	private String holder;

	// 持股比例（%）
	@JSONField(name="holding_scale",label="sign")
	private Double holdingScale;

	// 出资金额（万元）
	@JSONField(name="contribution_amount",label="sign")
	private Double contributionAmount;

	// 入股时间
	@JSONField(name="join_time",label="sign")
	private String joinTime;
	
	// 性别
	@JSONField(name="gender")
	private int gender;
	
	// 离职时间
	@JSONField(name="quit_time")
	private String quitTime;
	
	// 创建时间
	@JSONField(name="create_time")
	private String createTime;
	
	// 修改时间
	@JSONField(name="update_time")
	private String updateTime;

	public ShareholderModel(String clientId, String roundStr, String appKey,
			String token, String identifier, String reqId, String sign,
			String partnerType, String holder, Double holdingScale,
			Double contributionAmount, String joinTime, int gender,
			String quitTime, String createTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId, sign);
		this.partnerType = partnerType;
		this.holder = holder;
		this.holdingScale = holdingScale;
		this.contributionAmount = contributionAmount;
		this.joinTime = joinTime;
		this.gender = gender;
		this.quitTime = quitTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public ShareholderModel(String clientId, String roundStr,
			String appKey, String token, String identifier, String reqId,
			String partnerType, String sign) {
		super(clientId, roundStr, appKey, token, identifier, reqId, sign);
		this.partnerType = partnerType;
	}
	
}
