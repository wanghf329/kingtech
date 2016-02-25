package com.kingtech.utils;

import com.kingtech.dao.entity.RechargeRecord;
import com.kingtech.dao.entity.TrafficPrice;
import com.kingtech.model.RechargeRecordModel;
import com.kingtech.model.TrafficPriceModel;

public class OTDUtils {
	public static TrafficPrice getTrafficPrive(TrafficPriceModel trafficePrice) {
		if (trafficePrice != null) {
			return new TrafficPrice(trafficePrice.getSpecifications(),
					trafficePrice.getPrice(),
					trafficePrice.getTimeValidStart(),
					trafficePrice.getTimeValidEnd(), trafficePrice.getEnabled());
		}
		return null;
	}
	
	
	public static RechargeRecord getRechargeRecord(RechargeRecordModel rechargeRecord) {
		if (rechargeRecord != null) {
			return new RechargeRecord(rechargeRecord.getContractName(),
					rechargeRecord.getActivityName(),
					rechargeRecord.getOrderId(), rechargeRecord.getOrderName(),
					rechargeRecord.getAmount(), rechargeRecord.getDiscription());
		}
		return null;
	}	
}
