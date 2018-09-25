package com.kingtech.model;

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
public class ProvisionInfoModel extends BaseRequestModel {

	// 计提月份 YYYYMM
	@JSONField(name = "DATE_MONTH")
	private String dateMonth;

	// 正常类贷款余额（万元）
	@JSONField(name = "NORMAL_BALANCE")
	private String normalBalance;

	// 正常类计提比例（%）
	@JSONField(name = "NORMAL_RATE")
	private String normalRate;

	// 正常类计提金额（万元）
	@JSONField(name = "NORMAL_REAL")
	private String normalReal;

	// 关注类贷款余额（万元）
	@JSONField(name = "FOLLOW_BALANCE")
	private String followBalance;

	// 关注类计提比例（%）
	@JSONField(name = "FOLLOW_RATE")
	private String followRate;

	// 关注类计提金额（万元）
	@JSONField(name = "FOLLOW_REAL")
	private String followReal;

	// 次级类贷款余额（万元）
	@JSONField(name = "MINOR_BALANCE")
	private String minorBalance;

	// 次级类计提比例（%）
	@JSONField(name = "MINOR_RATE")
	private String minorRate;

	// 次级类计提金额（万元）
	@JSONField(name = "MINOR_REAL")
	private String minorReal;

	// 可疑类贷款余额（万元）
	@JSONField(name = "SUSPICIOUS_BALANCE")
	private String suspiciousBalance;

	// 可疑类计提比例（%）
	@JSONField(name = "SUSPICIOUS_RATE")
	private String suspiciousRate;

	// 可疑类计提金额（万元）
	@JSONField(name = "SUSPICIOUS_REAL")
	private String suspiciousReal;

	// 损失类贷款余额（万元）
	@JSONField(name = "LOSS_BALANCE")
	private String lossBalance;

	// 损失类计提比例（%）
	@JSONField(name = "LOSS_RATE")
	private String lossRate;

	// 损失类计提金额（万元）
	@JSONField(name = "LOSS_REAL")
	private String lossReal;

	@JSONField(name = "create_time")
	private String creatTime;

	@JSONField(name = "update_time")
	private String updateTime;

	public ProvisionInfoModel(String roundStr, String identifier, String reqId,
			String sign, String dateMonth,
			String normalBalance, String normalRate,
			String normalReal, String followBalance,
			String followRate, String followReal,
			String minorBalance, String minorRate,
			String minorReal, String suspiciousBalance,
			String suspiciousRate, String suspiciousReal,
			String lossBalance, String lossRate, String lossReal,
			String creatTime, String updateTime) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils
				.getToken(roundStr), identifier, reqId, sign, null);
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
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}

	public ProvisionInfoModel(String id, String dateMonth,
			String normalBalance, String normalRate,
			String normalReal, String followBalance,
			String followRate, String followReal,
			String minorBalance, String minorRate,
			String minorReal, String suspiciousBalance,
			String suspiciousRate, String suspiciousReal,
			String lossBalance, String lossRate, String lossReal) {
		super(id);
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
