package com.kingtech.web.commons.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.dao.entity.RepayExtendPlanInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.RepayExtendPlanDAO;
import com.kingtech.dao.rdbms.RepayExtendPlanInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.RepayExtendPlanInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
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
	private RepayExtendPlanInfoDAO repayExtendPlanInfoDAO;

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
			String count) {
		try {
			RepayExtendPlanInfo rp = null;
			if (StringUtils.isEmpty(id)) {
				rp = new RepayExtendPlanInfo(loanContractId, creatRequstId.getReqId(),PushStatus.INITATION,RecordStatus.NORMAL, count);
			} else {
				rp = repayExtendPlanInfoDAO.findOne(id);
				rp.setCount(count);
			}
			
			rp = repayExtendPlanInfoDAO.save(rp);
			
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
//			Contract ct = contractDAO.findOne(rf.getLoanContractId());
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
//		return new RepayExtendPlanModel(id, 
//				rp.getLoanContractId(), 
//				rp.getInterest().toPlainString(), 
//				rp.getEndDate()==null ? null : DateFormatUtils.format(rp.getEndDate(), "yyyy-MM-dd"), 
//				rp.getPrincipal().toPlainString(), 
//				rp.getCount());
		return null;
	}

	@Override
	public PagedResult<RepayExtendPlanInfoModel> pageList(Pageable pageAble) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	t1.ID, ");
		sql.append(" 	t1.LOAN_CONTRACT_ID, ");
		sql.append(" 	t1.CREATE_TIME, ");
		sql.append(" 	t2.CONTRACT_NUMBER, ");
		sql.append(" 	t2.CONTRACT_NAME, ");
		sql.append(" 	t1.PUSH_STATUS, ");
		sql.append(" 	t1.COUNT, ");
		sql.append("	t1.RECORD_STATUS ");
		sql.append(" FROM ");
		sql.append(" 	TB_LOAN_REPAY_EXTEND_PLAN_INFO t1, ");
		sql.append(" 	TB_LOAN_CONTRACT t2 ");
		sql.append(" WHERE ");
		sql.append(" 	t1.LOAN_CONTRACT_ID = t2.ID ");
		sql.append(" ORDER BY ");
		sql.append(" 	t1.LOAN_CONTRACT_ID, ");
		sql.append(" 	t1.COUNT DESC ");
		
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql.toString());
		Long count = dq.nativeQueryCount(sql.toString());
		
		List<RepayExtendPlanInfoModel> result = new ArrayList<RepayExtendPlanInfoModel>();
		for (Object[] obj : list) {
			RepayExtendPlanInfoModel model = new RepayExtendPlanInfoModel((String)obj[0], 
					(String)obj[1], 
					(String)obj[3],
					(String)obj[4], 
					(String)obj[6], 
					null, 
					PushStatus.valueOf(obj[5].toString()),
					RecordStatus.valueOf(obj[7].toString()));
			
			
			if (!model.getLoanContractId().isEmpty()) {
				List<RepayExtendPlanModel> plans = listByPlanInfoId(model.getId());
				model.setPlans(plans);
			}
			
			result.add(model);
		}
		return new PagedResult(result,count);
	}
	
	@Override
	public List<RepayExtendPlanModel> listByPlanInfoId(String repayExtendPlanInfoId){
		List<RepayExtendPlanModel> result = Lists.newArrayList();
		List<RepayExtendPlan> plans = repayExtendPlanDAO.listByRepayExtendPlanInfoId( repayExtendPlanInfoId);
		for (RepayExtendPlan repayExtendPlan : plans) {
			result.add(new RepayExtendPlanModel(repayExtendPlan.getId(),
					repayExtendPlan.getInterest(),
					repayExtendPlan.getEndDate(), 
					repayExtendPlan.getPrincipal()));
		}
		
		return result;
	}

	@Override
	public RepayExtendPlanInfoModel getPlanInfoById(String id) {
		RepayExtendPlanInfo info = repayExtendPlanInfoDAO.findOne(id);
		Contract contract = contractDAO.findOne(info.getLoanContractId());
		
		return new RepayExtendPlanInfoModel(info.getId(), 
				info.getLoanContractId(), 
				contract.getContractNumber(), 
				contract.getContractName(), 
				info.getCount(), 
				listByPlanInfoId(info.getId()), 
				info.getPushStatus(), 
				info.getRecordStatus());
		
	}

	@Override
	@Transactional
	public void addRepayExtendPlan(List<RepayExtendPlanModel> planModel) {
		for (RepayExtendPlanModel model : planModel) {
			RepayExtendPlan entity = null;
			if (StringUtils.isNotEmpty(model.getId())) {
				//修改
				entity = repayExtendPlanDAO.findOne(model.getId());
				entity.setEndDate(model.getEndDate());
				entity.setPrincipal(model.getPrincipal());
				entity.setInterest(model.getInterest());
			} else {
				entity = new RepayExtendPlan(model.getRepayExtendPlanInfoId(),
						model.getEndDate(), 
						model.getPrincipal(),
						model.getInterest());
			}
			
			repayExtendPlanDAO.save(entity);
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushRepayExtendPlanInfo(String id) {
		
		
	}

}
