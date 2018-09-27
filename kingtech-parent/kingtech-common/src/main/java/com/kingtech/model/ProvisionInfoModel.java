package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 计提信息
 * 
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionInfoModel extends BaseModel {
	private String id;

	// 计提月份 YYYYMM
	private String dateMonth;

	// 正常类贷款余额（万元）
	private String normalBalance;

	// 正常类计提比例（%）
	private String normalRate;

	// 正常类计提金额（万元）
	private String normalReal;

	// 关注类贷款余额（万元）
	private String followBalance;

	// 关注类计提比例（%）
	private String followRate;

	// 关注类计提金额（万元）
	private String followReal;

	// 次级类贷款余额（万元）
	private String minorBalance;

	// 次级类计提比例（%）
	private String minorRate;

	// 次级类计提金额（万元）
	private String minorReal;

	// 可疑类贷款余额（万元）
	private String suspiciousBalance;

	// 可疑类计提比例（%）
	private String suspiciousRate;

	// 可疑类计提金额（万元）
	private String suspiciousReal;

	// 损失类贷款余额（万元）
	private String lossBalance;

	// 损失类计提比例（%）
	private String lossRate;

	// 损失类计提金额（万元）
	private String lossReal;
}
