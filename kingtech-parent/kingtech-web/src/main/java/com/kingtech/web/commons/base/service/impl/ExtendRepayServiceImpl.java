package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.RepayExtendInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.ext.RepayExtendInfoModelExt;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ExtendRepayService;

@Slf4j
@Service
public class ExtendRepayServiceImpl implements ExtendRepayService{
	@Autowired
	private RepayExtendInfoDAO repayExtendInfoDao;
	
	@Autowired
	private ContractDAO contractDAO;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;

	@Override
	@Transactional
	public void addOrEdit(String id,String loanContractId, Date repayTime,
			BigDecimal money, BigDecimal interest,
			BigDecimal penaltyInterest, BigDecimal penalty,
			BigDecimal serviceCharge, BigDecimal otherCharge) {
		RepayExtendInfo ri = null;
		if (StringUtils.isEmpty(id)) {
			ri = new RepayExtendInfo(loanContractId,creatRequstId.getReqId(),
									PushStatus.INITATION,
									repayTime,
									money,
									interest,
									penaltyInterest,
									penalty,
									serviceCharge,
									otherCharge);
		}else{
			ri = repayExtendInfoDao.findOne(id);
//			ri.setExtendNum(extendNum);
//			ri.setRepayDate(repayDate);
//			ri.setRepayAmount(repayAmount);
//			ri.setRepayPrincipalAmount(repayPrincipalAmount);
//			ri.setRepayInterestAmount(repayInterestAmount);
		}
		repayExtendInfoDao.save(ri);
		paymentApi.repayExtendInfoApi(ri.getId(), StringUtils.isEmpty(id) ?  IdentifierType.A : IdentifierType.U);
	}

	@Override
	public List<RepayExtendInfoModelExt> listAll() {
		List<RepayExtendInfoModelExt> result = new ArrayList<RepayExtendInfoModelExt>();
		for(RepayExtendInfo rf : repayExtendInfoDao.findAll()){
			Contract ct = contractDAO.findOne(rf.getLoanContractId());
//			result.add(new RepayExtendInfoModelExt(rf.getId(), rf.getLoanContractId(),
//					String.valueOf(rf.getExtendNum()), DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"), 
//					rf.getRepayAmount().toPlainString(), 
//					rf.getRepayPrincipalAmount().toPlainString(), 
//					rf.getRepayInterestAmount().toPlainString(),ct.getLoanContractNo(),ct.getLoanContractName(),rf.getPushStatus()));
		}
		return result;
	}

	@Override
	public RepayExtendInfoModel getById(String id) {
		RepayExtendInfo rf = repayExtendInfoDao.findOne(id);
//		return new RepayExtendInfoModel(rf.getId(), rf.getLoanContractId(),
//										String.valueOf(rf.getExtendNum()), DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"), 
//										rf.getRepayAmount().toPlainString(), 
//										rf.getRepayPrincipalAmount().toPlainString(), 
//										rf.getRepayInterestAmount().toPlainString());
		return null;
	}
	
	@Override
	public PagedResult<RepayExtendInfoModelExt> pageList(Pageable pageAble) {
		String sql = "SELECT t1.ID,t1.LOAN_CONTRACT_ID,t1.EXTEND_NUM,t1.REPAY_DATE,t1.REPAY_AMOUNT,t1.REPAY_PRINCIPAL_AMOUNT,t1.REPAY_INTEREST_AMOUNT,t2.LOAN_CONTRACT_NO,t2.LOAN_CONTRACT_NAME,t1.PUSH_STATUS "
				+ "		FROM TB_LOAN_REPAY_EXTEND_INFO t1,TB_LOAN_CONTRACT t2 "
				+ "   WHERE t1.LOAN_CONTRACT_ID = t2.ID ORDER BY t1.LOAN_CONTRACT_ID,t1.REPAY_DATE DESC ";
		
		String[] params = new String[0];
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<RepayExtendInfoModelExt> result = new ArrayList<RepayExtendInfoModelExt>();
		for (Object[] obj : list) {
//			result.add(new RepayExtendInfoModelExt((String) obj[0],
//					(String) obj[1], ((Integer) obj[2]).toString(), DateFormatUtils.format(
//							(Date) obj[3], "yyyy-MM-dd"), ((BigDecimal) obj[4])
//							.toPlainString(), ((BigDecimal) obj[5])
//							.toPlainString(), ((BigDecimal) obj[6])
//							.toPlainString(), (String) obj[7], (String) obj[8],
//							PushStatus.valueOf(obj[9].toString())));
		}
		return new PagedResult(result,count);
	}
}
