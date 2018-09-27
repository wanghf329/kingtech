package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 计提信息
 * 
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionInfoRequestModel extends BaseRequestModel {

	// 计提月份 YYYYMM
	@JSONField(label="sign")
	private String dateMonth;

	// 正常类贷款余额（万元）
	@JSONField(label="sign")
	private String normalBalance;

	// 正常类计提比例（%）
	@JSONField(label="sign")
	private String normalRate;

	// 正常类计提金额（万元）
	@JSONField(label="sign")
	private String normalReal;

	// 关注类贷款余额（万元）
	@JSONField(label="sign")
	private String followBalance;

	// 关注类计提比例（%）
	@JSONField(label="sign")
	private String followRate;

	// 关注类计提金额（万元）
	@JSONField(label="sign")
	private String followReal;

	// 次级类贷款余额（万元）
	@JSONField(label="sign")
	private String minorBalance;

	// 次级类计提比例（%）
	@JSONField(label="sign")
	private String minorRate;

	// 次级类计提金额（万元）
	@JSONField(label="sign")
	private String minorReal;

	// 可疑类贷款余额（万元）
	@JSONField(label="sign")
	private String suspiciousBalance;

	// 可疑类计提比例（%）
	@JSONField(label="sign")
	private String suspiciousRate;

	// 可疑类计提金额（万元）
	@JSONField(label="sign")
	private String suspiciousReal;

	// 损失类贷款余额（万元）
	@JSONField(label="sign")
	private String lossBalance;

	// 损失类计提比例（%）
	@JSONField(label="sign")
	private String lossRate;

	// 损失类计提金额（万元）
	@JSONField(label="sign")
	private String lossReal;

	public ProvisionInfoRequestModel(String roundStr, String reqId, String dateMonth,
			String normalBalance, String normalRate, String normalReal,
			String followBalance, String followRate, String followReal,
			String minorBalance, String minorRate, String minorReal,
			String suspiciousBalance, String suspiciousRate,
			String suspiciousReal, String lossBalance, String lossRate,
			String lossReal) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.dateMonth = dateMonth;
		this.normalBalance = normalBalance;
		this.normalRate = normalRate;
		this.normalReal = normalReal;
		this.followBalance = followBalance;
		this.followRate = followRate;
		this.followReal = followReal;
		this.minorBalance = minorBalance;
		this.minorRate = minorRate;
		this.minorReal = minorReal;
		this.suspiciousBalance = suspiciousBalance;
		this.suspiciousRate = suspiciousRate;
		this.suspiciousReal = suspiciousReal;
		this.lossBalance = lossBalance;
		this.lossRate = lossRate;
		this.lossReal = lossReal;
	}
	
	
}
