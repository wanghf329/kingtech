package com.kingtech.web.commons.base.api.impl;

import java.util.ArrayList;
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
import com.kingtech.dao.entity.ContractDyw;
import com.kingtech.dao.entity.ContractZyw;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.OtherOverdueInfo;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.ContractDywDAO;
import com.kingtech.dao.rdbms.ContractZywDAO;
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
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.szsm.model.AsyReponseModel;
import com.kingtech.szsm.model.ContractDywRequestModel;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.ContractZywRequestModel;
import com.kingtech.szsm.model.EmployeeRequestModel;
import com.kingtech.szsm.model.EnterpriseCustomerRequestModel;
import com.kingtech.szsm.model.ExtendPlanRequestModel;
import com.kingtech.szsm.model.FinanceInfoRequestModel;
import com.kingtech.szsm.model.FinanceRepayPlanRequest;
import com.kingtech.szsm.model.GuaranteeRequestModel;
import com.kingtech.szsm.model.PersonalCustomerRequestModel;
import com.kingtech.szsm.model.RepayExtendInfoRequestModel;
import com.kingtech.szsm.model.RepayExtendPlanRequestModel;
import com.kingtech.szsm.model.RepayPlanRequestModel;
import com.kingtech.szsm.model.SettledInfoRequestModel;
import com.kingtech.szsm.model.SynResponseModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.utils.DTOUtils;
import com.kingtech.web.commons.http.service.FinanceService;


@Service
@Slf4j
public class PaymentApiImpl  implements PaymentApi {
	
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
	private ContractZywDAO collateralDAO;
	
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
	
	@Autowired 
	private ContractDywDAO contractDywDAO;
	
	
	
	

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
//			capitalModel = new CapitalModel(
//					roundStr,
//					type.name(), 
//					capital.getReqId(),
//					null, 
//					capital.getFinancingChannel(),
//					capital.getFinancingMoney().setScale(2).toPlainString(),
//					DateUtil.getDateStr(capital.getFinancingTime(), JSON.DEFFAULT_DATE_FORMAT), 
//					DateUtil.getDateStr(capital.getExpirationTime(), JSON.DEFFAULT_DATE_FORMAT),
//					capital.getReplyTime() == null ? null:DateUtil.getDateStr(capital.getReplyTime(), JSON.DEFFAULT_DATE_FORMAT), 
//				    DateUtil.getDateStr(capital.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT),
//				    capital.getUpdateTime() == null ? null:DateUtil.getDateStr(capital.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
//			
			
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
	public void employeeInfoApi(String capitalId,IdentifierType type) {
		
		
		   Employee employee = employeeDAO.findOne(capitalId);
		   if (employee ==null) {
				  return ;
			 }
		   
		   String roundStr =  RandomUtil.random8Len();
		   
		   EmployeeRequestModel employeeRequestModel = new EmployeeRequestModel(roundStr,
				   employee.getReqId(),
				   null, 
				   employee.getName(), 
				   employee.getPhone(),
				   employee.getEmail(), 
				   employee.getAddress(), 
				   employee.getDepartment(),
				   DTOUtils.getEnumIntVal(employee.getSex()), 
				   DTOUtils.getEnumIntVal(employee.getCardType()),
				   employee.getCardNumber(), 
				   DTOUtils.getEnumIntVal(employee.getEducation()),
				   DTOUtils.getEnumIntVal(employee.getExecutiveFlag()), 
				   employee.getPosition(),
				   DateUtil.getSimpleDate(employee.getEntryTime()), 
				   DateUtil.getSimpleDate(employee.getQuitTime()));
		   
		   
		   SynResponseModel responseModel= financeService.branchEmployeeFacade(employeeRequestModel,type);
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
		
//		if(!verifyResponse(reponseModel)){
//			log.info("验签未通过,reponseModel={}",reponseModel);
//			return ;
//		}
//		
//		PushStatus pushStatus = reponseModel.isSuccess() ? PushStatus.SUCCESS : PushStatus.FAILED;
//		Cmd cmd = Cmd.valueOf(reponseModel.getApi());
//		String reqId = reponseModel.getReqId();
//		
//		switch (cmd) {
//		
//		case pushCompanyInformation:  //机构基本信息
//			branchDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushEmployee:  //机构人员信息
//			employeeDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushInstitutionStockholder: //机构股东信息
//			shareholderDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushInstitutionCapital:  //机构资本信息
//			capitalDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushContract:	//放贷业务合同信息
//			contractDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushRepay:  //还款信息接口
//			repayInfoDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushExtendPlan://展期还款计划信息接口
//			repayExtendPlanDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushExtendRepay://展期还款信息接口
//			repayExtendInfoDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushBadDebt: //坏账信息接口
//			otherBaddebtDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushOverdue://逾期信息接口
//			otherOverdueInfoDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		case pushProvision://计提信息接口
//			provisionInfoDAO.updateStatusByReqId(reqId, pushStatus);
//			break;
//		
//		default:
//			break;
//		}
//		
	}

	@Override
	@Transactional
	public SynResponseModel contractInfoApi(String loanIdContractId,IdentifierType type) {
		
		Contract contract = contractDAO.findOne(loanIdContractId);
		if (contract == null) {
			log.info("未获取到相关数据loanIdContractId={}",loanIdContractId);
			return null;
		}
		
		String roundStr =  RandomUtil.random8Len();
		
	
		
		List<GuaranteeModel> guaranteeModels  = DTOUtils.getGuaranteeModels(guaranteeDAO.listByloanContractId(loanIdContractId));
		
		List<GuaranteeRequestModel> guaranteeRequestModels = null;
		if (!guaranteeModels.isEmpty()) {
			guaranteeRequestModels = new ArrayList<GuaranteeRequestModel>();
			for (GuaranteeModel s:guaranteeModels) {
				guaranteeRequestModels.add(new GuaranteeRequestModel(s.getName(), DTOUtils.getEnumIntVal(s.getCardType()), s.getCardNumber(), s.getAddress())); 
			}
		}
		PersonalCustomerRequestModel pcustomerRequestModel = null;
        EnterpriseCustomerRequestModel enterpriseCustomerRequestModel = null;
		if (BorrowerTypeEnum.S_2.equals(contract.getBorrowerType())) {
			PersonalCustomer personalCustomer = personalCustomerDao.findOne(contract.getBorrowerId());
			pcustomerRequestModel = new PersonalCustomerRequestModel(personalCustomer.getName(),DTOUtils.getEnumIntVal( personalCustomer.getSex()), DTOUtils.getEnumIntVal(personalCustomer.getCardType()), 
																	personalCustomer.getCardNumber(), personalCustomer.getPhone(), DTOUtils.getEnumIntVal( personalCustomer.getIsFarmer()), DTOUtils.getEnumIntVal( personalCustomer.getEducation()), personalCustomer.getEmail(), 
																	DTOUtils.getNewStr(personalCustomer.getIsMarry()), personalCustomer.getNationality(),DateUtil.getSimpleDate( personalCustomer.getBirthDate()), personalCustomer.getNation(), 
																	personalCustomer.getAddress(), personalCustomer.getPostCode(), personalCustomer.getRegisteredAddress(), 
																	personalCustomer.getNativePlace(), personalCustomer.getWorkUnit(), personalCustomer.getPosition());
		}else if (BorrowerTypeEnum.S_1.equals(contract.getBorrowerType())) {
			EnterpriseCustomer enterpriseCustomer = enterpriseCustomerDAO.findOne(contract.getBorrowerId());
			
			enterpriseCustomerRequestModel = new EnterpriseCustomerRequestModel(
					enterpriseCustomer.getName(), DTOUtils.getEnumIntVal( enterpriseCustomer.getScale()), DTOUtils.getEnumIntVal( enterpriseCustomer.getIndustryType()),  DTOUtils.getEnumIntVal(enterpriseCustomer.getIndustryinvolved()),
					enterpriseCustomer.getOrganizationcode(), enterpriseCustomer.getRegistCode(), enterpriseCustomer.getRegistOffice(),DateUtil.getSimpleDate(enterpriseCustomer.getRegistDate()), 
					enterpriseCustomer.getNationalTaxCode(), enterpriseCustomer.getLandTaxCode(), enterpriseCustomer.getLicenseCode(), DateUtil.getSimpleDate(enterpriseCustomer.getLicenceEndDate()),
					enterpriseCustomer.getLegalPerson(), DateUtil.getSimpleDate(enterpriseCustomer.getFoundDate()), enterpriseCustomer.getController(), enterpriseCustomer.getReallyCapital().toPlainString(), enterpriseCustomer.getBusinessScope(), 
					enterpriseCustomer.getRegisterAddress(), enterpriseCustomer.getAddress(), enterpriseCustomer.getPhone(), enterpriseCustomer.getLinkman(), enterpriseCustomer.getEmail(), enterpriseCustomer.getWebSite());
		}else {
			log.info("借款人类型存在 BorrowerType ={}",contract.getBorrowerType());
			return null;
		}
		
		List<ContractZyw> contractZywList = collateralDAO.listByloanContractId(loanIdContractId);
	
		List<ContractZywRequestModel> contractZywRequestModels = null;
		if (contractZywList != null && !contractZywList.isEmpty()) {
			contractZywRequestModels = new ArrayList<ContractZywRequestModel>();
			for (ContractZyw contractZyw : contractZywList) {
				contractZywRequestModels.add(new ContractZywRequestModel(DTOUtils.getEnumIntVal(contractZyw.getPledgeType()), contractZyw.getName(), contractZyw.getWorth().toPlainString(), contractZyw.getAddress(), contractZyw.getUnit()));
			}
		}
		
		
		List<ContractDyw> contractDywList = contractDywDAO.listByloanContractId(loanIdContractId);
		List<ContractDywRequestModel> contractDywRequestModels = null;
		if (contractDywList != null && !contractDywList.isEmpty()) {
			contractDywRequestModels = new ArrayList<ContractDywRequestModel>();
			for (ContractDyw contractDyw : contractDywList) {
				contractDywRequestModels.add(new ContractDywRequestModel(DTOUtils.getEnumIntVal(contractDyw.getPledgeType()), contractDyw.getName(), contractDyw.getWorth().toPlainString(), contractDyw.getAddress(), contractDyw.getUnit()));
			}
		}
		
		
		List<RepayPlan> repayPlanList = repayPlanDAO.listByloanContractId(loanIdContractId);
		List<RepayPlanRequestModel> repayPlanRequestModels = null;
	
		if (repayPlanList != null && !repayPlanList.isEmpty()) {
			repayPlanRequestModels  = new ArrayList<RepayPlanRequestModel>();
			for (RepayPlan repayPlan :repayPlanList) {
				repayPlanRequestModels.add(new RepayPlanRequestModel(DateUtil.getSimpleDate(repayPlan.getEndDate()), repayPlan.getMoney().toPlainString(), repayPlan.getInterest().toPlainString()));
			}
		}
		
		
		ContractRequestModel  contractRequestModel = new ContractRequestModel(
				roundStr,
				contract.getReqId(),
				null,
				contract.getContractNumber(),
				contract.getContractName(), 
				DTOUtils.getEnumIntVal(contract.getBorrowerType()), 
				contract.getMoney().toPlainString(), 
				DTOUtils.getEnumIntVal(contract.getTermType()), 
				contract.getTerm(), 
				DateUtil.getDateStr(contract.getStartDate(),"yyyy-MM-dd"), 
				DateUtil.getDateStr(contract.getEndDate(),"yyyy-MM-dd"),
				DTOUtils.getEnumIntVal(contract.getRateType()),
				contract.getRate().toPlainString(), 
				contract.getAnnualRate().toPlainString(),
				contract.getPlatformCost().toPlainString(),
				DTOUtils.getEnumIntVal(contract.getIsEntrust()), 
				DTOUtils.getEnumIntVal(contract.getPurpose()), 
				DTOUtils.getEnumIntVal(contract.getBusiness()),
				DTOUtils.getNewStr(contract.getLoanMethod()),
				DTOUtils.getEnumIntVal(contract.getRepayMethod()),
				DateUtil.getDateStr(contract.getSignTime(),"yyyy-MM-dd HH:mm:ss"),
				contract.getRepaySource(),
				contract.getDistrictCode(),
				pcustomerRequestModel, 
				enterpriseCustomerRequestModel,
				contractDywRequestModels, 
				contractZywRequestModels, 
				guaranteeRequestModels,
				repayPlanRequestModels);
		
		
		SynResponseModel responseModel = financeService.contractFacade(contractRequestModel,type);
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
//		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
//			infoModel = new RepayInfoModel(roundStr, 
//					                       type.name(),
//					                       repayInfo.getReqId(),
//					                       null,
//					                       contractDAO.findOne(repayInfo.getLoanContractId()).getLoanContractNo(),
//					                       repayInfo.getRepayAmount().toPlainString(),
//					                       repayInfo.getRepayPrincipalAmount().toPlainString(),
//					                       repayInfo.getRepayInterestAmount().toPlainString(),
//					                       DateUtil.getDateStr(repayInfo.getRepayDate(), "yyyy-MM-dd"),
//					                       DateUtil.getDateStr(repayInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
//					                       DateUtil.getDateStr(repayInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
//		}else {
//			log.info("还款信息暂不支持的操作 repayInfoId={},IdentifierType={} ",repayInfoId,type);
//			return;
//		}
		SynResponseModel responseModel = financeService.repayInfoFacade(infoModel);
		if (responseModel.isSuccess()) {
			repayInfo.setPushStatus(PushStatus.INPROSESS);
			repayInfoDAO.save(repayInfo);
		}
	}

	@Override
	@Transactional
	public SynResponseModel repayExtendInfoApi(String repayExtendInfoId, IdentifierType type) {
		
		RepayExtendInfo extendInfo = repayExtendInfoDAO.findOne(repayExtendInfoId);
		if (extendInfo == null ) {
			log.info("未获取到展期还款信息相关数据repayInfoId={}",repayExtendInfoId);
			return null;
		}
		
		
		
		String roundStr =  RandomUtil.random8Len();
		RepayExtendInfoRequestModel repayExtendInfoRequestModel = null;
		
		
//		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
//			extendInfoModel = new RepayExtendInfoModel(roundStr,
//					type.name(), 
//					extendInfo.getReqId(), null, contractDAO.findOne(extendInfo.getLoanContractId()).getLoanContractNo(),
//					DateUtil.getDateStr(extendInfo.getRepayTime(), "yyyy-MM-dd"),
//					extendInfo.getMoney().toPlainString(),
//					extendInfo.getInterest().toPlainString(),
//					extendInfo.getPenaltyInterest().toPlainString(),
//					DateUtil.getDateStr(extendInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
//                    DateUtil.getDateStr(extendInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
//		}else {
//			log.info("展期还款信息暂不支持的操作 repayExtendInfoId={},IdentifierType={} ",repayExtendInfoId,type);
//			return;
//		}
		SynResponseModel responseModel = financeService.repayExtendInfoFacade(null,null);
		if (responseModel.isSuccess()) {
			extendInfo.setPushStatus(PushStatus.INPROSESS);
			repayExtendInfoDAO.save(extendInfo);
		}
		return null;
	}

	@Override
	@Transactional
	public SynResponseModel repayExtendPlanApi(String repayExtendPlanId, IdentifierType type) {
		
		RepayExtendPlan extendPlan = repayExtendPlanDAO.findOne(repayExtendPlanId);
		if (extendPlan == null ) {
			log.info("未获取到展期还款计划相关数据repayExtendPlanId={}",repayExtendPlanId);
			return null;
		}
		
		List<RepayExtendPlan> repayExtendPlanlist = repayExtendPlanDAO.listByloanContractIdAndCount(extendPlan.getLoanContractId(), extendPlan.getCount());
		
		List<ExtendPlanRequestModel> planRequestModels = null;
		if (repayExtendPlanlist != null && !repayExtendPlanlist.isEmpty()) {
			
			planRequestModels = new ArrayList<ExtendPlanRequestModel>();
			for (RepayExtendPlan plan : repayExtendPlanlist) {
				planRequestModels.add(new ExtendPlanRequestModel(DateUtil.getSimpleDate(plan.getEndDate()), plan.getPrincipal().toPlainString(), plan.getInterest().toPlainString()));
			}
		}
		
		String roundStr =  RandomUtil.random8Len();
		RepayExtendPlanRequestModel repayExtendPlanRequestModel = new RepayExtendPlanRequestModel(roundStr, extendPlan.getReqId(), contractDAO.findOne(extendPlan.getLoanContractId()).getContractNumber(), extendPlan.getCount(), planRequestModels);
//		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
//			repayExtendPlanModel = new RepayExtendPlanModel(roundStr,
//					type.name(), extendPlan.getReqId(), null, contractDAO.findOne(extendPlan.getLoanContractId()).getLoanContractNo(),
//					extendPlan.getExtendCount()+"", extendPlan.getExtendTerm(),
//					DateUtil.getDateStr(extendPlan.getEndDate(), "yyyy-MM-dd"),
//					extendPlan.getPrincipal().toPlainString(), 
//					extendPlan.getReturnPrincipal().toPlainString(),
//					extendPlan.getInterest().toPlainString(),
//					extendPlan.getReturnInterest().toPlainString(),
//					DTOUtils.getNewStr(extendPlan.getStatus()), 
//					DTOUtils.getNewStr(extendPlan.getOverdueFlag()), 
//					extendPlan.getOverdueDays()+"",
//					DateUtil.getDateStr(extendPlan.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
//                    DateUtil.getDateStr(extendPlan.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
//		}else {
//			log.info("展期还款计划暂不支持的操作 repayExtendPlanId={},IdentifierType={} ",repayExtendPlanId,type);
//			return;
//		}
//		SynResponseModel responseModel = financeService.repayExtendPlanFacade(repayExtendPlanModel);
//		if (responseModel.isSuccess()) {
//			extendPlan.setPushStatus(PushStatus.INPROSESS);
//			repayExtendPlanDAO.save(extendPlan);
//		}
		return null;
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
//		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
//			otherBaddebtModel = new OtherBaddebtModel(roundStr,
//					type.name(),
//					otherBaddebt.getReqId(),
//					null,
//					contractDAO.findOne(otherBaddebt.getLoanContractId()).getLoanContractNo(),
//					otherBaddebt.getBadMoney().toPlainString(),
//					DateUtil.getDateStr(otherBaddebt.getSetDate(),"yyyy-MM-dd"), 
//					otherBaddebt.getFollowupWork(),
//					DateUtil.getDateStr(otherBaddebt.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
//                    DateUtil.getDateStr(otherBaddebt.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
//
//			
//		}else {
//			log.info("坏账暂不支持的操作 otherBaddebtId={},IdentifierType={} ",otherBaddebtId,type);
//			return;
//		}
		
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
					contractDAO.findOne(otherOverdueInfo.getLoanContractId()).getContractNumber(),
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
//		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
//			provisionInfoModel = new ProvisionInfoModel(roundStr, 
//					type.name(), 
//					provisionInfo.getReqId(), 
//					null, 
//					provisionInfo.getProvisionMoney().toPlainString(), 
//					DateUtil.getDateStr(provisionInfo.getProvisionDate(),"yyyy-MM-dd"), 
//					provisionInfo.getProvisionScale().toPlainString(),
//					DTOUtils.getNewStr(provisionInfo.getLoanClassification()),
//					provisionInfo.getBalance().toPlainString(), 
//					DateUtil.getDateStr(provisionInfo.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT), 
//                    DateUtil.getDateStr(provisionInfo.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
//			
//		}else {
//			log.info("计提暂不支持的操作 provisionInfoId={},IdentifierType={} ",provisionInfoId,type);
//			return;
//		}
		SynResponseModel responseModel = financeService.provisionInfoFacade(provisionInfoModel);
		if (responseModel.isSuccess()) {
			provisionInfo.setPushStatus(PushStatus.INPROSESS);
			provisionInfoDAO.save(provisionInfo);
		}
		
	}

	@Override
	public SynResponseModel settleInfoApi(String settleInfoId,
			IdentifierType type) {
		SettledInfo settle = settledInfoDAO.findOne(settleInfoId);
		if (settle == null) {
			log.info("未获取到放款相关数据settleInfoId={}",settleInfoId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();
		
		SettledInfoRequestModel infoRequestModel = new SettledInfoRequestModel(roundStr,
				settle.getReqId(),
				contractDAO.findOne(settle.getLoanContractId()).getContractNumber(),
				settle.getMoney().toPlainString(), 
				DateUtil.getDateStr(settle.getLoanTime(),JSON.DEFFAULT_DATE_FORMAT), 
				DateUtil.getSimpleDate(settle.getStartDate()), 
				DateUtil.getSimpleDate(settle.getEndDate()));
		
		SynResponseModel responseModel = financeService.settleInfoFacade(infoRequestModel, type);
		if (responseModel.isSuccess()) {
			settle.setPushStatus(PushStatus.INPROSESS);
			settledInfoDAO.save(settle);
		}
		return responseModel;
	}

	@Override
	public SynResponseModel financeInfoApi(String financeInfoId,IdentifierType type) {
		
		Capital capital = capitalDAO.findOne(financeInfoId);
		if (capital ==null) {
			log.info("未获取到融资信息相关数据financeInfoId={}",financeInfoId);
			  return null;
		  }
		String roundStr =  RandomUtil.random8Len();
		
		List<FinanceRepayPlanRequest> financeRepayPlanRequests = null;
		
		FinanceInfoRequestModel financeInfoRequestModel = new FinanceInfoRequestModel(roundStr,
				capital.getReqId(), 
				capital.getFinanceNumber(), 
				capital.getFinanceName(),
				capital.getLender(),
				DTOUtils.getEnumIntVal(capital.getChannel()), 
				capital.getMoney().toPlainString(),
				capital.getInterest().toPlainString(),
				capital.getCharge().toPlainString(), 
				capital.getGuaranteeMoney().toPlainString(), 
				capital.getRemark(), 
				DateUtil.getSimpleDate(capital.getFinanceDate()),
				DateUtil.getSimpleDate(capital.getEndDate()),
				capital.getRate().toPlainString(), 
				DTOUtils.getEnumIntVal(capital.getRateType()), 
				capital.getLoanContractNumber(),
				financeRepayPlanRequests);
		
		SynResponseModel responseModel = financeService.financeInfoFacade(financeInfoRequestModel, type);
		if (responseModel.isSuccess()) {
			capital.setPushStatus(PushStatus.INPROSESS);
			capitalDAO.save(capital);
		}
		return responseModel;
		
		
	}

}
