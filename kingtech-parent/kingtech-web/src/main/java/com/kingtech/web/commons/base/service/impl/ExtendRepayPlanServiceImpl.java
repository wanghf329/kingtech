package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.RepayExtendPlanDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RepayStatusEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.model.ext.RepayExtendPlanModelExt;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ExtendRepayPlanService;

@Service
public class ExtendRepayPlanServiceImpl implements ExtendRepayPlanService {

	@Autowired
	private RepayExtendPlanDAO repayExtendPlanDAO;

	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private ContractDAO contractDAO;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;

	@Override
	@Transactional
	public void addNew(String id, 
			String loanContractId,
			String count,
			String endDate, 
			String principal, 
			String interest) {
		try {
			RepayExtendPlan rp = null;
			if (StringUtils.isEmpty(id)) {
				rp = new RepayExtendPlan(loanContractId,
						creatRequstId.getReqId(),
						PushStatus.INITATION, 
						count,
						StringUtils.isEmpty(endDate) ? null : DateUtils.parseDate(endDate, "yyyy-MM-dd"), 
						new BigDecimal(principal), 
						new BigDecimal(interest));
			} else {
				rp = repayExtendPlanDAO.findOne(id);
				rp.setCount(count);
				rp.setEndDate(StringUtils.isEmpty(endDate) ? null : DateUtils.parseDate(endDate, "yyyy-MM-dd"));
				rp.setPrincipal(new BigDecimal(principal));
				rp.setInterest(new BigDecimal(interest));
			}
			
			rp = repayExtendPlanDAO.save(rp);
			
			IdentifierType type = StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U;
//			paymentApi.repayExtendPlanApi(rp.getId(), type);
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RepayExtendPlanModelExt> listAll() {
		List<RepayExtendPlanModelExt> result = new ArrayList<RepayExtendPlanModelExt>();
		for(RepayExtendPlan rf : repayExtendPlanDAO.findAll()){
			Contract ct = contractDAO.findOne(rf.getLoanContractId());
//			result.add(new RepayExtendPlanModelExt(rf.getId(), rf.getLoanContractId(),
//					String.valueOf(rf.getExtendCount()),rf.getExtendTerm(),DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"),
//					rf.getPrincipal().toPlainString(),
//					rf.getReturnPrincipal().toPlainString(),
//					rf.getInterest().toPlainString(),
//					rf.getReturnInterest().toPlainString(),
//					rf.getStatus().getKey(),
//					rf.getOverdueFlag().getKey(),
//					String.valueOf(rf.getOverdueDays()),
//					ct.getLoanContractNo(),ct.getLoanContractName(),rf.getPushStatus()));
		}
		return result;
	}
	
	@Override
	public RepayExtendPlanModel getById(String id) {
		RepayExtendPlan rp = repayExtendPlanDAO.findOne(id);
		return new RepayExtendPlanModel(id, 
				rp.getLoanContractId(), 
				rp.getInterest().toPlainString(), 
				rp.getEndDate()==null ? null : DateFormatUtils.format(rp.getEndDate(), "yyyy-MM-dd"), 
				rp.getPrincipal().toPlainString(), 
				rp.getCount());
	}

	@Override
	public PagedResult<ModelExt> pageList(Pageable pageAble) {
		String sql = "SELECT t1.ID,t1.LOAN_CONTRACT_ID,t1.CREATE_TIME,t1.END_DATE,t1.INTEREST,"
				+ " t1.PRINCIPAL,t2.CONTRACT_NUMBER,t2.CONTRACT_NAME,t1.PUSH_STATUS,t1.COUNT"
				+ "		FROM TB_LOAN_REPAY_EXTEND_PLAN t1,TB_LOAN_CONTRACT t2 " 
				+ "  WHERE t1.LOAN_CONTRACT_ID = t2.ID  ORDER BY t1.LOAN_CONTRACT_ID,t1.COUNT DESC ";
		
		String[] params = new String[0];
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<ModelExt> result = new ArrayList<ModelExt>();
		for (Object[] obj : list) {
			
			result.add(new ModelExt(new RepayExtendPlanModel((String)obj[0],
					(String)obj[1], 
					((BigDecimal)obj[4]).toPlainString(), 
					DateFormatUtils.format((Date)obj[3], "yyyy-MM-dd"), 
					((BigDecimal)obj[5]).toPlainString(),
					(String)obj[9]), 
					(String) obj[6], 
					(String) obj[7],
					PushStatus.valueOf(obj[8].toString())));
		}
		return new PagedResult(result,count);
	}

	
}
