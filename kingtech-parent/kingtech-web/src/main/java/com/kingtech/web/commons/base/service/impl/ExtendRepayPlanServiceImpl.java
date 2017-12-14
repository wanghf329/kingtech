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
	public void addNew(String id, String loanContractId, String extendCount, String extendTerm,
			String repayDate, String principal, String returnPrincipal,
			String interest, String returnInterest, String status,
			String overdueFlag, String overdueDays) {
		try {
			RepayExtendPlan rp = null;
			if(StringUtils.isEmpty(id)){
				rp = new RepayExtendPlan(
						loanContractId, 
						creatRequstId.getReqId(), 
						PushStatus.INITATION,
						Long.valueOf(extendCount), 
						extendTerm,
						StringUtils.isEmpty(repayDate) ? null : DateUtils.parseDate(repayDate, "yyyy-MM-dd"),
						new BigDecimal(principal), 
						new BigDecimal(returnPrincipal),
						new BigDecimal(interest), 
						new BigDecimal(returnInterest), 
						RepayStatusEnum.valueOf(status),
						YesNoEnum.valueOf(overdueFlag), 
						Long.valueOf(overdueDays));
			}else{
				rp = repayExtendPlanDAO.findOne(id);
				rp.setExtendCount(Long.valueOf(extendCount));
				rp.setExtendTerm(extendTerm);
				rp.setRepayDate(StringUtils.isEmpty(repayDate) ? null : DateUtils.parseDate(repayDate, "yyyy-MM-dd"));
				rp.setPrincipal(new BigDecimal(principal));
				rp.setReturnPrincipal(new BigDecimal(returnPrincipal));
				rp.setInterest(new BigDecimal(interest));
				rp.setReturnInterest(new BigDecimal(returnInterest));
				rp.setStatus(RepayStatusEnum.valueOf(status));
				rp.setOverdueFlag(YesNoEnum.valueOf(overdueFlag));
				rp.setOverdueDays(Long.valueOf(overdueDays));
			}
			rp = repayExtendPlanDAO.save(rp);
			
			IdentifierType type = StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U;
			paymentApi.repayExtendPlanApi(rp.getId(), type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RepayExtendPlanModelExt> listAll() {
		List<RepayExtendPlanModelExt> result = new ArrayList<RepayExtendPlanModelExt>();
		for(RepayExtendPlan rf : repayExtendPlanDAO.findAll()){
			Contract ct = contractDAO.findOne(rf.getLoanContractId());
			result.add(new RepayExtendPlanModelExt(rf.getId(), rf.getLoanContractId(),
					String.valueOf(rf.getExtendCount()),rf.getExtendTerm(),DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"),
					rf.getPrincipal().toPlainString(),
					rf.getReturnPrincipal().toPlainString(),
					rf.getInterest().toPlainString(),
					rf.getReturnInterest().toPlainString(),
					rf.getStatus().getKey(),
					rf.getOverdueFlag().getKey(),
					String.valueOf(rf.getOverdueDays()),
					ct.getLoanContractNo(),ct.getLoanContractName(),rf.getPushStatus()));
		}
		return result;
	}
	
	@Override
	public RepayExtendPlanModel getById(String id) {
		RepayExtendPlan rp = repayExtendPlanDAO.findOne(id);
		return new RepayExtendPlanModel(id, 
				rp.getLoanContractId(),
				Long.toString(rp.getExtendCount()),
				rp.getExtendTerm(),
				rp.getRepayDate()==null ? null : DateFormatUtils.format(rp.getRepayDate(), "yyyy-MM-dd"),
				rp.getPrincipal().toPlainString(),
				rp.getReturnPrincipal().toPlainString(),
				rp.getInterest().toPlainString(),
				rp.getReturnInterest().toPlainString(),
				rp.getStatus().getKey(),
				rp.getOverdueFlag().getKey(),
				Long.toString(rp.getOverdueDays()));
	}

	@Override
	public PagedResult<ModelExt> pageList(Pageable pageAble) {
		String sql = "SELECT t1.ID,t1.LOAN_CONTRACT_ID,t1.EXTEND_COUNT,t1.EXTEND_TERM,t1.REPAY_DATE,t1.PRINCIPAL,t1.RETURN_PRINCIPAL,t1.INTEREST,t1.RETURN_INTEREST,t1.OVERDUE_FLAG,t1.OVERDUE_DAYS,"
				+ "      t1.`STATUS`,t2.LOAN_CONTRACT_NO,t2.LOAN_CONTRACT_NAME,t1.PUSH_STATUS"
				+ "		FROM TB_LOAN_REPAY_EXTEND_PLAN t1,TB_LOAN_CONTRACT t2 " 
				+ "   WHERE t1.LOAN_CONTRACT_ID = t2.ID ORDER BY t1.LOAN_CONTRACT_ID,t1.REPAY_DATE DESC ";
		
		String[] params = new String[0];
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<ModelExt> result = new ArrayList<ModelExt>();
		for (Object[] obj : list) {
			result.add(new ModelExt(new RepayExtendPlanModel((String)obj[0], (String)obj[1],
					((Integer) obj[2]).toString(),
					(String)obj[3],
					DateFormatUtils.format((Date)obj[4], "yyyy-MM-dd"),
					((BigDecimal)obj[5]).toPlainString(),
					((BigDecimal)obj[6]).toPlainString(),
					((BigDecimal)obj[7]).toPlainString(),
					((BigDecimal)obj[8]).toPlainString(),
					RepayStatusEnum.valueOf((String)obj[11]).getKey(),
					YesNoEnum.valueOf((String)obj[9]).getKey(),
					((String)obj[3])),
					
					(String) obj[12], 
					(String) obj[13],
					PushStatus.valueOf(obj[14].toString())));
		}
		return new PagedResult(result,count);
	}
}