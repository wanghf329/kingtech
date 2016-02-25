package com.kingtech.utils;

import com.kingtech.dao.entity.RechargeRecord;
import com.kingtech.dao.entity.TrafficPrice;
import com.kingtech.model.RechargeRecordModel;
import com.kingtech.model.TrafficPriceModel;

public class DTOUtils {
	public static TrafficPriceModel getTrafficPriveModel(TrafficPrice trafficePrice) {
		if (trafficePrice != null) {
			return new TrafficPriceModel(trafficePrice.getId(),
					trafficePrice.getSpecifications(),
					trafficePrice.getPrice(),
					trafficePrice.getTimeValidStart(),
					trafficePrice.getTimeValidEnd(), trafficePrice.getEnabled());
		}
		return null;
	}
	
	
	public static RechargeRecordModel getRechargeRecordModel(RechargeRecord rechargeRecord) {
		if (rechargeRecord != null) {
			return new RechargeRecordModel(rechargeRecord.getId(),
					rechargeRecord.getContractName(),
					rechargeRecord.getActivityName(),
					rechargeRecord.getOrderId(), rechargeRecord.getOrderName(),
					rechargeRecord.getAmount(), rechargeRecord.getDiscription());
		}
		return null;
	}	
}
