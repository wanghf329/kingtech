package com.kingtech.web.commons.base.service;





public interface ShareholderService {
	
	
	public void addNew(String partnerType,
			String holder, String holdingScale, String contributionAmount,
			String joinTime, String gender, String quitTime, String branchId);
}
