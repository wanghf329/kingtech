package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
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
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.ContractDywModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.ContractZywModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.RepayPlanModel;
import com.kingtech.model.SettledInfoModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
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
	
	@Autowired
	private PaymentApi api;
	
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
		int index = 1;
		for(ContractDywModel d : dyw)
			dywDao.save(new ContractDyw(loanContractId, index++, d.getPledgeType(), 
						d.getName(), d.getWorth(), d.getAddress(), d.getUnit()));
	}
	
	@Override
	@Transactional
	public void addZyw(String loanContractId,List<ContractZywModel> zyw){
		zywDao.deleteByLoanContractId(loanContractId);
		int index = 1;
		for(ContractZywModel z : zyw)
			zywDao.save(new ContractZyw(loanContractId, index++,z.getPledgeType(), 
						z.getName(), z.getWorth(), z.getAddress(), z.getUnit()));
	}
	
	@Override
	@Transactional
	public void addGuarantee(String loanContractId,List<GuaranteeModel> guaranteeList) {
		guaranteeDAO.deleteByLoanContractId(loanContractId);
		int index = 1;
		for (GuaranteeModel g : guaranteeList) {
			guaranteeDAO.save(new Guarantee(loanContractId,index++,g.getName(),g.getCardType(),g.getCardNumber(),g.getAddress()));
		}
	}

	@Override
	@Transactional
	public void addRepayPlan(String loanContractId, List<RepayPlanModel> repayPlanList) {
		repayPlanDAO.deleteByLoanContractId(loanContractId);
		int index = 1;
		for (RepayPlanModel re : repayPlanList) {
			repayPlanDAO.save(new RepayPlan(loanContractId,index++,re.getEndDate(),re.getMoney(),re.getInterest()));
		}
	}

	@Override
	@Transactional
	public void addSettledInfo(String loanContractId, BigDecimal money, Date loanTime, Date startDate, Date endDate) {
		SettledInfo entity = settledInfoDAO.findByloanContractId(loanContractId);
		IdentifierType type = null;
		if (entity == null) {
			entity = settledInfoDAO.save(new SettledInfo(loanContractId, creatRequstId.getReqId(), 
												PushStatus.INITATION, money, loanTime, startDate, endDate,RecordStatus.NORMAL));
			type = IdentifierType.A;
		} else {
			entity.setMoney(money);
			entity.setLoanTime(loanTime);
			entity.setStartDate(startDate);
			entity.setEndDate(endDate);
			settledInfoDAO.save(entity);
			type = IdentifierType.U;
		}
		api.settleInfoApi(entity.getId(), type);
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
	public PagedResult<SettledInfoModel> pageListSettledInfo(Pageable pageAble){
		String sql = " SELECT t1.LOAN_CONTRACT_ID,t2.CONTRACT_NUMBER,t2.CONTRACT_NAME,t1.MONEY,t1.LOAN_TIME,t1.START_DATE,t1.END_DATE,t1.PUSH_STATUS,t1.ID"
				+ " from TB_LOAN_CONTRACT_SETTLED t1,TB_LOAN_CONTRACT t2 where t1.LOAN_CONTRACT_ID = t2.ID and t1.RECORD_STATUS != 'DELETEED' order by t1.CREATE_TIME";
		String[] params = new String[0];
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<SettledInfoModel> result = new ArrayList<SettledInfoModel>();
		for(Object[] obj : list){
			result.add(new SettledInfoModel((String) obj[0], (String) obj[2],
					(String) obj[1], (BigDecimal) obj[3], 
					DateFormatUtils.format((Date) obj[4], "yyyy-MM-dd"),
					DateFormatUtils.format((Date) obj[5], "yyyy-MM-dd"),
					DateFormatUtils.format((Date) obj[6], "yyyy-MM-dd"),
					PushStatus.valueOf((String)obj[7]),(String) obj[8]));
		}
		
		return new PagedResult(result,count);
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
	
	@Override
	@Transactional
	public void syncSettledInfoPushStatus(){
		settledInfoDAO.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS,PushStatus.DELETEING)).forEach(s->{
			api.queryTranInfoApi(s.getId(), Cmd.loanInfo);
		});;
	}
}
