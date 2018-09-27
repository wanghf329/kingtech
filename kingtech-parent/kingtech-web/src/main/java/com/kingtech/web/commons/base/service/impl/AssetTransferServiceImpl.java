package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.AssetTransfer;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.rdbms.AssetTransferDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.AssetTransferModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.AssetTransferService;

@Slf4j
@Service
public class AssetTransferServiceImpl implements AssetTransferService{
	@Autowired
	private AssetTransferDAO assetTransferDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;

	@Override
	@Transactional
	public void addOrEdit(String id, String loanContractId, String transferNumber,
			BigDecimal transferMoney, BigDecimal originalMoney,
			BigDecimal discountMoney, String acceptUnit, String protocol,
			Date transferDate) {
		AssetTransfer at = null;
		if(StringUtils.isEmpty(id)){
			at = new AssetTransfer(loanContractId, creatRequstId.getReqId(), PushStatus.INITATION, RecordStatus.NORMAL,
					transferNumber, transferMoney, originalMoney, discountMoney, acceptUnit, protocol,
					transferDate);
		}else{
			at = assetTransferDao.findOne(id);
			at.setTransferNumber(transferNumber);
			at.setTransferMoney(transferMoney);
			at.setOriginalMoney(originalMoney);
			at.setDiscountMoney(discountMoney);
			at.setAcceptUnit(acceptUnit);
			at.setProtocol(protocol);
			at.setTransferDate(transferDate);
		}
		assetTransferDao.save(at);
		
//		paymentApi.provisionInfoApi(pi.getId(),StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
	}

	@Override
	public List<AssetTransfer> listAll() {
		return (List<AssetTransfer>)assetTransferDao.findAll();
	}

	@Override
	public AssetTransferModel getById(String id) {
		AssetTransfer at = assetTransferDao.findOne(id);
		return new AssetTransferModel(at.getId(), 
				at.getLoanContractId(),
				at.getTransferNumber(),
				at.getTransferMoney().toPlainString(),
				at.getOriginalMoney().toPlainString(),
				at.getDiscountMoney().toPlainString(),
				at.getAcceptUnit(),
				at.getProtocol(),
				DateFormatUtils.format(at.getTransferDate(),"yyyy-MM-dd"));
	}
	
}
