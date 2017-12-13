package com.kingtech.web.commons.base.api.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.common.utils.RandomUtil;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.OtherOverdueInfo;
import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.CollateralDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.OtherBaddebtDAO;
import com.kingtech.dao.rdbms.OtherOverdueInfoDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.dao.rdbms.ProvisionInfoDAO;
import com.kingtech.dao.rdbms.RepayExtendInfoDAO;
import com.kingtech.dao.rdbms.RepayExtendPlanDAO;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.dao.rdbms.RepayPlanDAO;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.dao.rdbms.ShareholderDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.AsyReponseModel;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.CollateralModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.RepayPlanModel;
import com.kingtech.model.SettledInfoModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.BaseAbstract;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.utils.DTOUtils;
import com.kingtech.web.commons.http.service.FinanceService;


@Service
@Slf4j
public class PaymentApiImpl extends BaseAbstract implements PaymentApi {
	
	@Autowired
	private BranchDAO branchDAO;
	
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private CapitalDAO capitalDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private ShareholderDAO shareholderDAO;
	
	@Autowired 
	private ContractDAO contractDAO;
	
	@Autowired 
	private CollateralDAO collateralDAO;
	
	@Autowired 
	private EnterpriseCustomerDAO enterpriseCustomerDAO;
	
	@Autowired 
	private GuaranteeDAO guaranteeDAO;
	
	@Autowired 
	private PersonalCustomerDAO personalCustomerDao;
	
	@Autowired 
	private RepayPlanDAO repayPlanDAO;
	
	@Autowired 
	private SettledInfoDAO settledInfoDAO;
	
	@Autowired
	private RepayInfoDAO repayInfoDAO;
	
	@Autowired
	private RepayExtendInfoDAO repayExtendInfoDAO;
	
	@Autowired
	private RepayExtendPlanDAO repayExtendPlanDAO;
	
	@Autowired
	private OtherBaddebtDAO otherBaddebtDAO;
	
	@Autowired
	private OtherOverdueInfoDAO otherOverdueInfoDAO;
	
	@Autowired
	private ProvisionInfoDAO provisionInfoDAO;
	
	

	@Override
	public void branchInfoApi(String branchId, IdentifierType type) {
		
	   Branch branch =	branchDAO.findOne(branchId);
	   if (branch ==null) {
		  return ;
	  }
	   
	   String roundStr =  RandomUtil.random8Len();
	   BranchInfoModel branchInfoModel =null;
	   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
		        branchInfoModel = new BranchInfoModel(
		           roundStr, 
                   type.name(), 
                   branch.getReqId(), 
                   null, 
                   branch.getCorporateName(),
                   branch.getLegalRepresentative(),
                   branch.getRegDapital().setScale(2).toPlainString(),
                   DateUtil.getDateStr(branch.getBuildDate(), "yyyy-MM-dd"),
                   DateUtil.getDateStr(branch.getOpeningDate(), "yyyy-MM-dd"), 
                   branch.getBusinessAddr(), 
                   branch.getSiteArea(),
                   branch.getOrganizationCode(),
                   branch.getLicence(), 
                   branch.getNationalRegNum(), 
                   branch.getLandRegNum(), 
                   branch.getBusinessScope(), 
                   DateUtil.getDateStr(branch.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT),
                   branch.getUpdateTime() == null? null:DateUtil.getDateStr(branch.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
		}else {
//			branchInfoModel = new BranchInfoModel( roundStr, type.name(), branch.getReqId(), branch.getLicence());
			log.info("机构基本信息暂时不支持删除");
			return;
			
		}
		   
	   SynResponseModel responseModel= financeService.branchInfoFacade(branchInfoModel);
	   if (responseModel.isSuccess()) {
		   branch.setPushStatus(PushStatus.INPROSESS);
		   branchDAO.save(branch);
	   }
	   
	  

	}

	@Override
	public void capitalInfoApi(String capitalId, IdentifierType type) {
		Capital capital = capitalDAO.findOne(capitalId);
		if (capital ==null) {
			  return ;
		  }
		String roundStr =  RandomUtil.random8Len();
	    CapitalModel  capitalModel =null;
		if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			capitalModel = new CapitalModel(
					roundStr,
					type.name(), 
					capital.getReqId(),
					null, 
					capital.getFinancingChannel(),
					capital.getFinancingMoney().setScale(2).toPlainString(),
					DateUtil.getDateStr(capital.getFinancingTime(), JSON.DEFFAULT_DATE_FORMAT), 
					DateUtil.getDateStr(capital.getExpirationTime(), JSON.DEFFAULT_DATE_FORMAT),
					capital.getReplyTime() == null ? null:DateUtil.getDateStr(capital.getReplyTime(), JSON.DEFFAULT_DATE_FORMAT), 
				    DateUtil.getDateStr(capital.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT),
				    capital.getUpdateTime() == null ? null:DateUtil.getDateStr(capital.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
			
			
		}else {
//			capitalModel = new CapitalModel(roundStr, type.name(), capital.getReqId());
			
			log.info("机构资本信息暂时不支持删除");
			return;
		}
		
		SynResponseModel responseModel= financeService.branchCapitalFacade(capitalModel);
		   if (responseModel.isSuccess()) {
			   capital.setPushStatus(PushStatus.INPROSESS);
			   capitalDAO.save(capital);
		   }
		   
	}

	@Override
	@Transactional
	public void employeeInfoApi(String capitalId, IdentifierType type) {
		
		
		   Employee employee = employeeDAO.findOne(capitalId);
		   if (employee ==null) {
				  return ;
			 }
		   
		   String roundStr =  RandomUtil.random8Len();
		   
		   EmployeeModel employeeModel = null;
		   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			   employeeModel = new EmployeeModel(roundStr,
					   type.name(),
					   employee.getReqId(), 
					   null, 
					   employee.getName(), 
					   employee.getPhone(),
					   employee.getEmail(),
					   employee.getPostalAddress(),
					   employee.getDepartment(),
					   employee.getSex(), 
					   employee.getIdNumber(),
					   employee.getEducation(),
					   employee.getExecutiveFlag()+"",
					   employee.getPost(),
					   DateUtil.getDateStr(employee.getReplyTime(), "yyyy-MM-dd"), 
					   DateUtil.getDateStr(employee.getEntryTime(), "yyyy-MM-dd"),
					   employee.getStatus().getKey(), 
					   DateUtil.getDateStr(employee.getQuitTime(), "yyyy-MM-dd"), 
					   DateUtil.getDateStr(employee.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT), 
					   DateUtil.getDateStr(employee.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
		}else {
			log.info("机构人员信息暂时不支持删除");
			return;
		}
		   
		   SynResponseModel responseModel= financeService.branchEmployeeFacade(employeeModel);
		   if (responseModel.isSuccess()) {
			   employee.setPushStatus(PushStatus.INPROSESS);
			   employeeDAO.save(employee);
		   }
	}

	@Override
	@Transactional
	public void shareholderInfoApi(String holderId, IdentifierType type) {
		Shareholder shareholder =shareholderDAO.findOne(holderId) ;
		 if (shareholder ==null) {
			  return ;
		 }
		  String roundStr =  RandomUtil.random8Len();
		   
		  ShareholderModel shareholderModel = null;
		   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			   shareholderModel = new ShareholderModel(roundStr, 
					   type.name(), 
					   shareholder.getReqId(), 
					   null,
					   shareholder.getPartnerType().getKey(), 
					   shareholder.getHolder(),
					   shareholder.getHoldingScale().setScale(2).toPlainString(),
					   shareholder.getContributionAmount().setScale(2).toPlainString(), 
					   DateUtil.getDateStr(shareholder.getJoinTime(), "yyyy-MM-dd"),
					   shareholder.getGender() == null ? null : shareholder.getGender().getKey(), 
					   DateUtil.getDateStr(shareholder.getQuitTime(), "yyyy-MM-dd"), 
					   DateUtil.getDateStr(shareholder.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT), 
					   DateUtil.getDateStr(shareholder.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
		   }else {
				log.info("机构股东信息暂时不支持删除");
				return;
			}
		 
		   SynResponseModel responseModel= financeService.branchShareholderFacade(shareholderModel);
		   if (responseModel.isSuccess()) {
			   shareholder.setPushStatus(PushStatus.INPROSESS);
			   shareholderDAO.save(shareholder);
		   }
		
	}

	@Override
	@Transactional
	public void handleResult(AsyReponseModel reponseModel) {
		
		if(!verifyResponse(reponseModel)){
			log.info("验签未通过,reponseModel={}",reponseModel);
			return ;
		}
		
		PushStatus pushStatus = reponseModel.isSuccess() ? PushStatus.SUCCESS : PushStatus.FAILED;
		Cmd cmd = Cmd.valueOf(reponseModel.getApi());
		String reqId = reponseModel.getReqId();
		
		switch (cmd) {
		
		case pushCompanyInformation:  //机构基本信息
			branchDAO.updateStatusByReqId(reqId, pushStatus);
			break;
		case pushEmployee:  //机构人员信息
			employeeDAO.updateStatusByReqId(reqId, pushStatus);
			break;
		case pushInstitutionStockholder: //机构股东信息
			shareholderDAO.updateStatusByReqId(reqId, pushStatus);
			break;
		case pushInstitutionCapital:  //机构资本信息
			capitalDAO.updateStatusByReqId(reqId, pushStatus);
			break;
		case pushContract:	//放贷业务合同信息
			contractDAO.updateStatusByReqId(reqId, pushStatus);
			break;
		
		default:
			break;
		}
		
	}

	@Override
	@Transactional
	public SynResponseModel contractInfoApi(String loanIdContractId, IdentifierType type) {
		
		Contract contract = contractDAO.findOne(loanIdContractId);
		if (contract == null) {
			log.info("未获取到相关数据loanIdContractId={}",loanIdContractId);
			return null;
		}
		
		String roundStr =  RandomUtil.random8Len();
		
		List<GuaranteeModel> guaranteeModels  = DTOUtils.getGuaranteeModels(guaranteeDAO.listByloanContractId(loanIdContractId));
		
	    SettledInfoModel settledInfoModel = DTOUtils.getSettledInfoModels(settledInfoDAO.getByloanContractId(loanIdContractId));
		
		List<RepayPlanModel> repayPlanModels = DTOUtils.getRepayPlanModels(repayPlanDAO.listByloanContractId(loanIdContractId));
		
		List<CollateralModel> collateralModels = DTOUtils.getCollateralModels(collateralDAO.listByloanContractId(loanIdContractId));
		
		
		ContractModel contractModel = null;
		if (IdentifierType.A.equals(type)) {
			contractModel = new ContractModel(roundStr,
					                          IdentifierType.A.name(),
					                          contract.getReqId(), 
					                          null,
					                          contract.getLoanContractId(),
					                          contract.getLoanContractName(), 
					                          DTOUtils.getNewStr(contract.getBorrowerType()), 
					                          contract.getCustomerId(),
					                          contract.getGuarantee(), 
					                          contract.getLoanAmount().toPlainString(),
					                          DTOUtils.getNewStr(contract.getPayType()),
					                          contract.getPeriodTerm()+"", 
					                          DateUtil.getDateStr(contract.getLoanStartDate(), "yyyy-MM-dd"),
					                          DateUtil.getDateStr(contract.getLoanEndDate(), "yyyy-MM-dd"),
					                          DTOUtils.getNewStr(contract.getRateType()), 
					                          contract.getRate().toPlainString(),
					                          DTOUtils.getNewStr(contract.getPurpose()), 
					                          DTOUtils.getNewStr(contract.getIndustry()),
					                          DTOUtils.getNewStr(contract.getLoanType()),
					                          DTOUtils.getNewStr(contract.getUnionFlag()),
					                          DTOUtils.getNewStr(contract.getPayType()),
					                          DateUtil.getDateStr(contract.getSignDate(),JSON.DEFFAULT_DATE_FORMAT), 
					                          contract.getRepaySource(),
					                          contract.getStatus().getKey(),
					                          DTOUtils.getNewStr(contract.getIsExtend()), 
					                          DateUtil.getDateStr(contract.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT),
					                          DateUtil.getDateStr(contract.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT),
					                          null,
					                          collateralModels.isEmpty()?null:JSON.toJSONString(collateralModels),
					                          guaranteeModels.isEmpty()?null:JSON.toJSONString(guaranteeModels),
					                          repayPlanModels.isEmpty()?null:JSON.toJSONString(repayPlanModels),
					                          settledInfoModel == null ?null:JSON.toJSONString(settledInfoModel)) ;
			String customerStr ="{\"customerType\":\"$1\",\"$3\":$2}";
			String dataStr =null;
			if (BorrowerTypeEnum.S_1.equals(contract.getBorrowerType())) {
				EnterpriseCustomerModel enterpriseCustomerModel = DTOUtils.getEnterpriseCustomerModel(enterpriseCustomerDAO.findOne(contract.getBorrowerId()));
				dataStr = enterpriseCustomerModel ==null ?null :JSON.toJSONString(enterpriseCustomerModel);
				customerStr = customerStr.replace("$1", "E").replace("$3", "corporateCustomer");
			}else {
				PersonalCustomerModel personalCustomerModel = DTOUtils.getPersonalCustomerModel(personalCustomerDao.findOne(contract.getBorrowerId()));
				dataStr = personalCustomerModel ==null ?null :JSON.toJSONString(personalCustomerModel);
				customerStr = customerStr.replace("$1", "P").replace("$3", "personalCustomer");
			}
			customerStr = customerStr.replace("$2", dataStr);
			contractModel.setLoanCustomerPackage(customerStr);
			
			
		}else {
			log.info("暂不支持的操作 loanIdContractId={},IdentifierType={} ",loanIdContractId,type);
			return null;
		}
		
		SynResponseModel responseModel = financeService.contractFacade(contractModel);
		if (responseModel.isSuccess()) {
			contract.setPushStatus(PushStatus.INPROSESS);
			contractDAO.save(contract);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public void repayInfoApi(String repayInfoId, IdentifierType type) {
		
		RepayInfo repayInfo = repayInfoDAO.findOne(repayInfoId);
		if (repayInfo == null ) {
			log.info("未获取到还款信息相关数据repayInfoId={}",repayInfoId);
			return;
		}
		
		RepayInfoModel infoModel = null;
		String roundStr =  RandomUtil.random8Len();
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			infoModel = new RepayInfoModel(roundStr, 
					                       type.name(),
					                       repayInfo.getReqId(),
					                       null,
					                       repayInfo.getLoanContractId(),
					                       repayInfo.getRepayAmount().toPlainString(),
					                       repayInfo.getRepayPrincipalAmount().toPlainString(),
					                       repayInfo.getRepayInterestAmount().toPlainString(),
					                       DateUtil.getDateStr(repayInfo.getRepayDate(), "yyyy-MM-dd"),
					                       DateUtil.getDateStr(repayInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
					                       DateUtil.getDateStr(repayInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
		}else {
			log.info("还款信息暂不支持的操作 repayInfoId={},IdentifierType={} ",repayInfoId,type);
			return;
		}
		SynResponseModel responseModel = financeService.repayInfoFacade(infoModel);
		if (responseModel.isSuccess()) {
			repayInfo.setPushStatus(PushStatus.INPROSESS);
			repayInfoDAO.save(repayInfo);
		}
	}

	@Override
	@Transactional
	public void repayExtendInfoApi(String repayExtendInfoId, IdentifierType type) {
		
		RepayExtendInfo extendInfo = repayExtendInfoDAO.findOne(repayExtendInfoId);
		if (extendInfo == null ) {
			log.info("未获取到展期还款信息相关数据repayInfoId={}",repayExtendInfoId);
			return;
		}
		
		String roundStr =  RandomUtil.random8Len();
		RepayExtendInfoModel extendInfoModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			extendInfoModel = new RepayExtendInfoModel(roundStr,
					type.name(), 
					extendInfo.getReqId(), null, contractDAO.findOne(extendInfo.getLoanContractId()).getLoanContractId(), extendInfo.getExtendNum()+"", 
					DateUtil.getDateStr(extendInfo.getRepayDate(), "yyyy-MM-dd"),
					extendInfo.getRepayAmount().toPlainString(),
					extendInfo.getRepayPrincipalAmount().toPlainString(),
					extendInfo.getRepayInterestAmount().toPlainString(),
					DateUtil.getDateStr(extendInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
                    DateUtil.getDateStr(extendInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
		}else {
			log.info("展期还款信息暂不支持的操作 repayExtendInfoId={},IdentifierType={} ",repayExtendInfoId,type);
			return;
		}
		SynResponseModel responseModel = financeService.repayExtendInfoFacade(extendInfoModel);
		if (responseModel.isSuccess()) {
			extendInfo.setPushStatus(PushStatus.INPROSESS);
			repayExtendInfoDAO.save(extendInfo);
		}
		
	}

	@Override
	@Transactional
	public void repayExtendPlanApi(String repayExtendPlanId, IdentifierType type) {
		
		RepayExtendPlan extendPlan = repayExtendPlanDAO.findOne(repayExtendPlanId);
		if (extendPlan == null ) {
			log.info("未获取到展期还款计划相关数据repayExtendPlanId={}",repayExtendPlanId);
			return;
		}
		
		String roundStr =  RandomUtil.random8Len();
		RepayExtendPlanModel repayExtendPlanModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			repayExtendPlanModel = new RepayExtendPlanModel(roundStr,
					type.name(), extendPlan.getReqId(), null, extendPlan.getLoanContractId(),
					extendPlan.getExtendCount()+"", extendPlan.getExtendTerm(),
					DateUtil.getDateStr(extendPlan.getRepayDate(), "yyyy-MM-dd"),
					extendPlan.getPrincipal().toPlainString(), 
					extendPlan.getReturnPrincipal().toPlainString(),
					extendPlan.getInterest().toPlainString(),
					extendPlan.getReturnInterest().toPlainString(),
					DTOUtils.getNewStr(extendPlan.getStatus()), 
					DTOUtils.getNewStr(extendPlan.getOverdueFlag()), 
					extendPlan.getOverdueDays()+"",
					DateUtil.getDateStr(extendPlan.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
                    DateUtil.getDateStr(extendPlan.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
		}else {
			log.info("展期还款计划暂不支持的操作 repayExtendPlanId={},IdentifierType={} ",repayExtendPlanId,type);
			return;
		}
		SynResponseModel responseModel = financeService.repayExtendPlanFacade(repayExtendPlanModel);
		if (responseModel.isSuccess()) {
			extendPlan.setPushStatus(PushStatus.INPROSESS);
			repayExtendPlanDAO.save(extendPlan);
		}
	}

	@Override
	@Transactional
	public void otherBaddebtApi(String otherBaddebtId, IdentifierType type) {
		OtherBaddebt otherBaddebt = otherBaddebtDAO.findOne(otherBaddebtId);
		if (otherBaddebt == null ) {
			log.info("未获取到坏账相关数据otherBaddebtId={}",otherBaddebtId);
			return;
		}
		String roundStr =  RandomUtil.random8Len();
		
		OtherBaddebtModel otherBaddebtModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			otherBaddebtModel = new OtherBaddebtModel(roundStr,
					type.name(),
					otherBaddebt.getReqId(),
					null,
					otherBaddebt.getLoanContractId(),
					otherBaddebt.getBadMoney().toPlainString(),
					DateUtil.getDateStr(otherBaddebt.getSetDate(),"yyyy-MM-dd"), 
					otherBaddebt.getFollowupWork(),
					DateUtil.getDateStr(otherBaddebt.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
                    DateUtil.getDateStr(otherBaddebt.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));

			
		}else {
			log.info("坏账暂不支持的操作 otherBaddebtId={},IdentifierType={} ",otherBaddebtId,type);
			return;
		}
		
		SynResponseModel responseModel = financeService.otherBaddebtFacade(otherBaddebtModel);
		if (responseModel.isSuccess()) {
			otherBaddebt.setPushStatus(PushStatus.INPROSESS);
			otherBaddebtDAO.save(otherBaddebt);
		}
	}

	@Override
	@Transactional
	public void otherOverdueInfoApi(String otherOverdueInfoId,IdentifierType type) {
		OtherOverdueInfo otherOverdueInfo = otherOverdueInfoDAO.findOne(otherOverdueInfoId);
		if (otherOverdueInfo == null ) {
			log.info("未获取到逾期相关数据otherOverdueInfoId={}",otherOverdueInfoId);
			return;
		}
		String roundStr =  RandomUtil.random8Len();
		
		OtherOverdueInfoModel otherOverdueInfoModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			otherOverdueInfoModel = new OtherOverdueInfoModel(roundStr, 
					
					
					type.name(), 
					otherOverdueInfo.getReqId(), 
					null, 
					otherOverdueInfo.getLoanContractId(),
					otherOverdueInfo.getOverdueMoney().toPlainString(), 
					DateUtil.getDateStr(otherOverdueInfo.getOverdueDate(),"yyyy-MM-dd"), 
					otherOverdueInfo.getOverdueInterest().toPlainString(), 
					otherOverdueInfo.getBalance().toPlainString(),
					otherOverdueInfo.getRemarks(), 
					DateUtil.getDateStr(otherOverdueInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
                    DateUtil.getDateStr(otherOverdueInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
			
		}else {
			log.info("逾期暂不支持的操作 otherOverdueInfoId={},IdentifierType={} ",otherOverdueInfoId,type);
			return;
		}
		
		SynResponseModel responseModel = financeService.otherOverdueInfoFacade(otherOverdueInfoModel);
		if (responseModel.isSuccess()) {
			otherOverdueInfo.setPushStatus(PushStatus.INPROSESS);
			otherOverdueInfoDAO.save(otherOverdueInfo);
		}
		
	}

	@Override
	@Transactional
	public void provisionInfoApi(String provisionInfoId, IdentifierType type) {
		ProvisionInfo provisionInfo = provisionInfoDAO.findOne(provisionInfoId);
		if (provisionInfo == null ) {
			log.info("未获取到计提相关数据provisionInfoId={}",provisionInfoId);
			return;
		}
		String roundStr =  RandomUtil.random8Len();
		ProvisionInfoModel provisionInfoModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			provisionInfoModel = new ProvisionInfoModel(roundStr, 
					type.name(), 
					provisionInfo.getReqId(), 
					null, 
					provisionInfo.getLoanContractId(), 
					provisionInfo.getProvisionMoney().toPlainString(), 
					DateUtil.getDateStr(provisionInfo.getProvisionDate(),"yyyy-MM-dd"), 
					provisionInfo.getProvisionScale().toPlainString(),
					DTOUtils.getNewStr(provisionInfo.getLoanClassification()),
					provisionInfo.getBalance().toPlainString(), 
					DateUtil.getDateStr(provisionInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
                    DateUtil.getDateStr(provisionInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
			
		}else {
			log.info("计提暂不支持的操作 provisionInfoId={},IdentifierType={} ",provisionInfoId,type);
			return;
		}
		SynResponseModel responseModel = financeService.provisionInfoFacade(provisionInfoModel);
		if (responseModel.isSuccess()) {
			provisionInfo.setPushStatus(PushStatus.INPROSESS);
			provisionInfoDAO.save(provisionInfo);
		}
		
	}

}
