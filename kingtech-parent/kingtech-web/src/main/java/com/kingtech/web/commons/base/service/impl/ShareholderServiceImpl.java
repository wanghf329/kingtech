package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.ShareholderDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.web.commons.base.service.ShareholderService;

@Service
public class ShareholderServiceImpl implements ShareholderService{
	
	@Autowired
	private ShareholderDAO shareholderDao;

	@Override
	@Transactional
	public void addNew(String id, String partnerType, String holder, String holdingScale, String contributionAmount,
			String joinTime, String gender, String quitTime, String branchId) {
		try {
			shareholderDao.save(new Shareholder(partnerType,holder,
					new BigDecimal(holdingScale),
					new BigDecimal(contributionAmount),
					DateUtils.parseDate(joinTime, "yyyy-MM-dd"),
					Integer.valueOf(gender),
					DateUtils.parseDate(quitTime, "yyyy-MM-dd"),branchId,
					"11100011",PushStatus.INPROSESS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
