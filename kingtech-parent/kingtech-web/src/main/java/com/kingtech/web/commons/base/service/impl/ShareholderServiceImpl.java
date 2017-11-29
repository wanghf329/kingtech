package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.ShareholderDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PartnerTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.SexEnum;
import com.kingtech.model.ShareholderModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ShareholderService;

@Service
public class ShareholderServiceImpl implements ShareholderService {

	@Autowired
	private ShareholderDAO shareholderDao;

	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;

	@Override
	@Transactional
	public void addNew(String id, String partnerType, String holder,
			String holdingScale, String contributionAmount, String joinTime,
			String gender, String quitTime, String branchId) {
		try {
			Shareholder sh = null;
			if(StringUtils.isEmpty(id)){
				sh = new Shareholder(PartnerTypeEnum.getValue(partnerType),
									holder, 
									new BigDecimal(holdingScale), 
									new BigDecimal(contributionAmount), 
									StringUtils.isEmpty(joinTime) ? null : DateUtils.parseDate(joinTime, "yyyy-MM-dd"),
									SexEnum.getValue(gender),
									StringUtils.isEmpty(quitTime) ? null : DateUtils.parseDate(quitTime, "yyyy-MM-dd"), 
									branchId,
									creatRequstId.getReqId(), 
									PushStatus.INITATION);
			}else{
				sh = shareholderDao.findOne(id);
				sh.setPartnerType(PartnerTypeEnum.getValue(partnerType));
				sh.setHolder(holder);
				sh.setHoldingScale(new BigDecimal(holdingScale));
				sh.setContributionAmount(new BigDecimal(contributionAmount));
				sh.setJoinTime(StringUtils.isEmpty(joinTime) ? null : DateUtils.parseDate(joinTime,"yyyy-MM-dd"));
				sh.setGender(SexEnum.getValue(gender));
				sh.setQuitTime(StringUtils.isEmpty(quitTime) ? null : DateUtils.parseDate(quitTime, "yyyy-MM-dd"));
				sh.setBranchId(branchId);
			}
			
			sh = shareholderDao.save(sh);
			
			IdentifierType type = StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U;
			paymentApi.shareholderInfoApi(sh.getId(), type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Shareholder> listAll() {
		return (List<Shareholder>) shareholderDao.findAll();
	}

	@Override
	public ShareholderModel getById(String id) {
		Shareholder sh = shareholderDao.findOne(id);
		return new ShareholderModel(id, 
				sh.getPartnerType().getKey(), 
				sh.getHolder(),
				sh.getHoldingScale().toPlainString(), 
				sh.getContributionAmount().toPlainString(),
				DateFormatUtils.format(sh.getJoinTime(), "yyyy-MM-dd"),
				sh.getGender().getKey(), 
				sh.getQuitTime()==null ? null : DateFormatUtils.format(sh.getQuitTime(), "yyyy-MM-dd"));
	}

}
