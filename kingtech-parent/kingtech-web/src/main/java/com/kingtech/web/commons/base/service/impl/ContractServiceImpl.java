package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.ContractDyw;
import com.kingtech.dao.entity.ContractZyw;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.ContractDywDAO;
import com.kingtech.dao.rdbms.ContractZywDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.dao.rdbms.RepayPlanDAO;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.ContractDywModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.ContractZywModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.service.ContractService;
/**
 * 合同信息
 * @author XA_JKWHF
 *
 */
@Service
public class ContractServiceImpl implements ContractService{
	@Autowired
	private ContractDAO contractDao;
	
	@Autowired
	private GuaranteeDAO guaranteeDAO;
	
	@Autowired
	private RepayPlanDAO repayPlanDAO;
	
	@Autowired
	private SettledInfoDAO settledInfoDAO;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PersonalCustomerDAO personalCustomerDao;
	
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Autowired
	private ContractZywDAO zywDao;
	
	@Autowired
	private ContractDywDAO dywDao;
	
	@Autowired
	private DynamicQuery dq;
	
	@Override
	public List<Contract> listAll(){
		List<Contract> list = (List<Contract>)contractDao.findAll();
		for(Contract ct : list){
			if(BorrowerTypeEnum.S_1.equals(ct.getBorrowerType())){
				ct.setBorrowerName(enterpriseDao.findOne(ct.getBorrowerId()).getName());
			}else{
				ct.setBorrowerName(personalCustomerDao.findOne(ct.getBorrowerId()).getName());
			}
		}
		return list;
	}
	
	@Override
	public List<Contract> listByUserIdAndPushstatus(String userId,PushStatus... pushStatus){
		return (List<Contract>)contractDao.listByUserIdAndPushstatus(userId, Arrays.asList(pushStatus));
	}

	@Override
	@Transactional
	public void save(ContractModel model){
		try{
			Contract ct = null;
			if(StringUtils.isEmpty(model.getId())){
				ct = new Contract();
				BeanUtils.copyProperties(ct, model);
				ct.setPushStatus(PushStatus.INITATION);
				ct.setReqId(creatRequstId.getReqId());
			}else{
				ct = contractDao.findOne(model.getId());
				String reqId = ct.getReqId();
				BeanUtils.copyProperties(ct, model);
				ct.setId(model.getId());
				ct.setPushStatus(ct.getPushStatus());
				ct.setReqId(reqId);
			}
			contractDao.save(ct);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Contract getById(String id) {
		return contractDao.findOne(id);
	}
	
	@Override
	@Transactional
	public void addDyw(String loanContractId,List<ContractDywModel> dyw){
		dywDao.deleteByLoanContractId(loanContractId);
		for(ContractDywModel d : dyw)
			dywDao.save(new ContractDyw(loanContractId, d.getPledgeType(), 
						d.getName(), d.getWorth(), d.getAddress(), d.getUnit()));
	}
	
	@Override
	@Transactional
	public void addZyw(String loanContractId,List<ContractZywModel> zyw){
		zywDao.deleteByLoanContractId(loanContractId);
		for(ContractZywModel z : zyw)
			zywDao.save(new ContractZyw(loanContractId, z.getPledgeType(), 
						z.getName(), z.getWorth(), z.getAddress(), z.getUnit()));
	}
	
	@Override
	@Transactional
	public void addGuarantee(String loanContractId, String[] name,
			String[] cardNum, String[] phone, String[] address) {
		guaranteeDAO.deleteByLoanContractId(loanContractId);
		for (int i = 1; i < name.length; i++) {
//			Guarantee guarantee = new Guarantee(loanContractId, name[i], cardNum[i], phone[i], address[i]);
//			guaranteeDAO.save(guarantee);
		}
	}

	@Override
	@Transactional
	public void addRepayPlan(String loanContractId, String[] repayDate,
			BigDecimal[] principal, BigDecimal[] interest) {
//		try {
//			repayPlanDAO.deleteByLoanContractId(loanContractId);
//			for (int i = 1; i < repayDate.length; i++) {
//				RepayPlan repayPlan = new RepayPlan(loanContractId, 
//						DateUtils.parseDate(repayDate[i], "yyyy-MM-dd"),
//						principal[i], interest[i]);
//				repayPlanDAO.save(repayPlan);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	@Transactional
	public void addSettledInfo(String loanContractId, BigDecimal[] money,
			String[] loanDate, String[] debtStartDate, String[] debtEndDate) {
//		try {
//			settledInfoDAO.deleteByLoanContractId(loanContractId);
//			for (int i = 1; i < money.length; i++) {
//				SettledInfo settledInfo = new SettledInfo(loanContractId, money[i], 
//						DateUtils.parseDate(loanDate[i], "yyyy-MM-dd"), 
//						DateUtils.parseDate(debtStartDate[i], "yyyy-MM-dd"), 
//						DateUtils.parseDate(debtEndDate[i], "yyyy-MM-dd"));
//				settledInfoDAO.save(settledInfo);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public List<ContractZyw> listContractZyw(String loanContractId){
		return zywDao.listByloanContractId(loanContractId);
	}
	@Override
	public List<ContractDyw> listContractDyw(String loanContractId){
		return dywDao.listByloanContractId(loanContractId);
	}

	@Override
	public List<Guarantee> listGuaranteeByLoanContractId(String loanContractId) {
		return (List<Guarantee>)guaranteeDAO.listByloanContractId(loanContractId);
	}
	
	@Override
	public List<RepayPlan> listRepayPlanByLoanContractId(String loanContractId) {
		return (List<RepayPlan>)repayPlanDAO.listByloanContractId(loanContractId);
	}
	
	@Override
	public List<SettledInfo> listSettledInfoByLoanContractId(String loanContractId) {
		return (List<SettledInfo>)settledInfoDAO.listByloanContractId(loanContractId);
	}

	@Override
	public PagedResult<ContractModel> pageList(Pageable pageAble) {
		String sql = " SELECT * from TB_LOAN_CONTRACT t order by t.CREATE_TIME DESC ";
		
		String[] params = new String[0];
		List<Contract> list = dq.nativeQueryPagingList(Contract.class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<Contract> result = new ArrayList<Contract>();
		for(Contract ct : list){
			if(BorrowerTypeEnum.S_1.equals(ct.getBorrowerType())){
				ct.setBorrowerName(enterpriseDao.findOne(ct.getBorrowerId()).getName());
			}else{
				ct.setBorrowerName(personalCustomerDao.findOne(ct.getBorrowerId()).getName());
			}
			result.add(ct);
		}
		
		return new PagedResult(result,count);
	}
}
