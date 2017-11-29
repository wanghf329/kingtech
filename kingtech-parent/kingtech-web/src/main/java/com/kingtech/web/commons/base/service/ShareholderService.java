package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Shareholder;
import com.kingtech.model.ShareholderModel;





public interface ShareholderService {
	
	
	public void addNew(String id, String partnerType,
			String holder, String holdingScale, String contributionAmount,
			String joinTime, String gender, String quitTime, String branchId);
	
	
	public  List<Shareholder> listAll();
	
	public ShareholderModel getById(String id);
}
