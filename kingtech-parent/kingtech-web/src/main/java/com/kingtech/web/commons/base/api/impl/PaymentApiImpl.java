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
import com.kingtech.dao.entity.AssetTransfer;
import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.dao.entity.BranchAccountInfo;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.ContractDyw;
import com.kingtech.dao.entity.ContractZyw;
import com.kingtech.dao.entity.DayEndDz;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.dao.entity.FinanceRepayPlan;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.AssetTransferDAO;
import com.kingtech.dao.rdbms.BranchAccountBalanceDAO;
import com.kingtech.dao.rdbms.BranchAccountInfoDAO;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.ContractDywDAO;
import com.kingtech.dao.rdbms.ContractZywDAO;
import com.kingtech.dao.rdbms.DayEndDzDAO;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.FinanceMonthBalanceDAO;
import com.kingtech.dao.rdbms.FinanceRepayPlanDAO;
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.OtherBaddebtDAO;
import com.kingtech.dao.rdbms.OtherOverdueInfoDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.dao.rdbms.ProvisionInfoDAO;
import com.kingtech.dao.rdbms.RepayExtendInfoDAO;
import com.kingtech.dao.rdbms.RepayExtendPlanDAO;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.dao.rdbms.RepayPlanDAO;
import com.kingtech.dao.rdbms.RepaymentFinanceDao;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.dao.rdbms.ShareholderDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.szsm.model.AssetTransferRequestModel;
import com.kingtech.szsm.model.AsyReponseModel;
import com.kingtech.szsm.model.BranchAccountBalanceRequest;
import com.kingtech.szsm.model.BranchAccountInfoRequest;
import com.kingtech.szsm.model.ContractDywRequestModel;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.ContractZywRequestModel;
import com.kingtech.szsm.model.DayEndDzRequestModel;
import com.kingtech.szsm.model.EmployeeRequestModel;
import com.kingtech.szsm.model.EnterpriseCustomerRequestModel;
import com.kingtech.szsm.model.FinanceInfoRequestModel;
import com.kingtech.szsm.model.FinanceMonthBalanceRequest;
import com.kingtech.szsm.model.FinanceRepayPlanRequest;
import com.kingtech.szsm.model.GuaranteeRequestModel;
import com.kingtech.szsm.model.OtherBaddebtRequestModel;
import com.kingtech.szsm.model.PersonalCustomerRequestModel;
import com.kingtech.szsm.model.ProvisionInfoRequestModel;
import com.kingtech.szsm.model.QueryInfoRequestModel;
import com.kingtech.szsm.model.RepayExtendInfoRequestModel;
import com.kingtech.szsm.model.RepayInfoRequestModel;
import com.kingtech.szsm.model.RepayPlanRequestModel;
import com.kingtech.szsm.model.RepaymentFinanceRequestModel;
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
	private ContractZywDAO contractZywDAO;
	
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
	
	@Autowired 
	private AssetTransferDAO assetTransferDAO;
	
	@Autowired
	private RepaymentFinanceDao repaymentFinanceDao;
	
	@Autowired
	private FinanceMonthBalanceDAO financeMonthBalanceDAO;
	
	@Autowired
	private BranchAccountInfoDAO branchAccountInfoDAO;
	
	@Autowired
	private BranchAccountBalanceDAO branchAccountBalanceDAO;
	
	@Autowired
	private FinanceRepayPlanDAO financeRepayPlanDAO;
	
	@Autowired
	private DayEndDzDAO dayEndDzDAO;
	
	


	@Override
	@Transactional
	public SynResponseModel employeeInfoApi(String capitalId,IdentifierType type) {
		
		
		   Employee employee = employeeDAO.findOne(capitalId);
		   if (employee ==null) {
				  return null;
			 }
		   
		   String roundStr =  RandomUtil.random8Len();
		   EmployeeRequestModel employeeRequestModel = null;
		   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			     employeeRequestModel = new EmployeeRequestModel(roundStr,
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
			     employee.setPushStatus(PushStatus.INPROSESS);
			   
		   }else{
			   employeeRequestModel = new EmployeeRequestModel(roundStr,employee.getReqId());
			   employee.setPushStatus(PushStatus.DELETEING);
		   }
		   SynResponseModel responseModel= financeService.branchEmployeeFacade(employeeRequestModel,type);
		   if (responseModel.isSuccess()) {
			   employeeDAO.save(employee);
		   }else {
			   throw new RuntimeException();
		}
		   return responseModel;
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
		
		ContractRequestModel contractRequestModel = null;
		String roundStr =  RandomUtil.random8Len();
		Contract contract = contractDAO.findOne(loanIdContractId);
		
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {

				if (contract == null) {
					log.info("未获取到相关数据loanIdContractId={}",loanIdContractId);
					return null;
				}
				
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
				
				List<ContractZyw> contractZywList = contractZywDAO.listByloanContractId(loanIdContractId);
				
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
				
				
				contractRequestModel = new ContractRequestModel(
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
			contract.setPushStatus(PushStatus.INPROSESS);
		}else{
			contractRequestModel = new ContractRequestModel(roundStr,contract.getReqId());
			contract.setPushStatus(PushStatus.DELETEING);
		}
		
		
		SynResponseModel responseModel = financeService.contractFacade(contractRequestModel,type);
		if (responseModel.isSuccess()) {
			contractDAO.save(contract);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel repayInfoApi(String repayInfoId, IdentifierType type) {
		
		RepayInfo repayInfo = repayInfoDAO.findOne(repayInfoId);
		if (repayInfo == null ) {
			log.info("未获取到还款信息相关数据repayInfoId={}",repayInfoId);
			return null;
		}
		
		
		String roundStr =  RandomUtil.random8Len();
		RepayInfoRequestModel infoModel = new RepayInfoRequestModel(roundStr,
																 repayInfo.getReqId(), 
																 contractDAO.findOne(repayInfo.getLoanContractId()).getContractNumber(),
																 DateUtil.getDateStr(repayInfo.getRepayTime(), "yyyy-MM-dd"), 
																 repayInfo.getMoney().toPlainString(), 
																 repayInfo.getInterest().toPlainString(), 
																 repayInfo.getPenaltyInterest().toPlainString(), 
																 repayInfo.getPenalty().toPlainString(),
																 repayInfo.getServiceCharge().toPlainString(), 
																 repayInfo.getOtherCharge().toPlainString());
		SynResponseModel responseModel = financeService.repayInfoFacade(infoModel,type);
		if (responseModel.isSuccess()) {
			repayInfo.setPushStatus(PushStatus.INPROSESS);
			repayInfoDAO.save(repayInfo);
		}
		return responseModel;
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
		RepayExtendInfoRequestModel repayExtendInfoRequestModel = new RepayExtendInfoRequestModel(roundStr,
				extendInfo.getReqId(),
				contractDAO.findOne(extendInfo.getLoanContractId()).getContractNumber(),
				DateUtil.getSimpleDate(extendInfo.getRepayTime()),
				extendInfo.getMoney().toPlainString(),
				extendInfo.getInterest().toPlainString(),
				extendInfo.getPenaltyInterest().toPlainString(),
				extendInfo.getPenalty().toPlainString(),
				extendInfo.getServiceCharge().toPlainString(), 
				extendInfo.getOtherCharge().toPlainString());
		SynResponseModel responseModel = financeService.repayExtendInfoFacade(repayExtendInfoRequestModel,type);
		if (responseModel.isSuccess()) {
			extendInfo.setPushStatus(PushStatus.INPROSESS);
			repayExtendInfoDAO.save(extendInfo);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel repayExtendPlanApi(String repayExtendPlanId, IdentifierType type) {
		
		RepayExtendPlan extendPlan = repayExtendPlanDAO.findOne(repayExtendPlanId);
		if (extendPlan == null ) {
			log.info("未获取到展期还款计划相关数据repayExtendPlanId={}",repayExtendPlanId);
			return null;
		}
		
//		List<RepayExtendPlan> repayExtendPlanlist = repayExtendPlanDAO.listByRepayExtendPlanInfoId(extendPlan.get, extendPlan.getCount());
		
//		List<ExtendPlanRequestModel> planRequestModels = null;
//		if (repayExtendPlanlist != null && !repayExtendPlanlist.isEmpty()) {
//			
//			planRequestModels = new ArrayList<ExtendPlanRequestModel>();
//			for (RepayExtendPlan plan : repayExtendPlanlist) {
//				planRequestModels.add(new ExtendPlanRequestModel(DateUtil.getSimpleDate(plan.getEndDate()), plan.getPrincipal().toPlainString(), plan.getInterest().toPlainString()));
//			}
//		}
		
//		String roundStr =  RandomUtil.random8Len();
//		RepayExtendPlanRequestModel repayExtendPlanRequestModel = new RepayExtendPlanRequestModel(roundStr, extendPlan.getReqId(), contractDAO.findOne(extendPlan.getLoanContractId()).getContractNumber(), extendPlan.getCount(), planRequestModels);
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
	public SynResponseModel otherBaddebtApi(String otherBaddebtId, IdentifierType type) {
		OtherBaddebt otherBaddebt = otherBaddebtDAO.findOne(otherBaddebtId);
		if (otherBaddebt == null ) {
			log.info("未获取到坏账相关数据otherBaddebtId={}",otherBaddebtId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();
		
		OtherBaddebtRequestModel otherBaddebtModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			otherBaddebtModel = new  OtherBaddebtRequestModel(roundStr, 
					                                        otherBaddebt.getReqId(),
					                                        contractDAO.findOne(otherBaddebt.getLoanContractId()).getContractNumber(),
					                                        otherBaddebt.getBadMoney().toPlainString(),
					                                        DateUtil.getSimpleDate(otherBaddebt.getLossDate()),
					                                        DTOUtils.getEnumIntVal(otherBaddebt.getBadType()), 
					                                        otherBaddebt.getFollowUp());
			otherBaddebt.setPushStatus(PushStatus.INPROSESS);
		}else {
			otherBaddebtModel = new OtherBaddebtRequestModel(roundStr, otherBaddebt.getReqId());
			otherBaddebt.setPushStatus(PushStatus.DELETEING);
		}

		
		SynResponseModel responseModel = financeService.otherBaddebtFacade(otherBaddebtModel,type);
		if (responseModel.isSuccess()) {
			otherBaddebtDAO.save(otherBaddebt);
		}else {
			throw  new RuntimeException();
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel provisionInfoApi(String provisionInfoId, IdentifierType type) {
		ProvisionInfo provisionInfo = provisionInfoDAO.findOne(provisionInfoId);
		if (provisionInfo == null ) {
			log.info("未获取到计提相关数据provisionInfoId={}",provisionInfoId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();
		ProvisionInfoRequestModel provisionInfoModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			provisionInfoModel = new ProvisionInfoRequestModel(roundStr,
					provisionInfo.getReqId(),
					DateUtil.getDateStr(provisionInfo.getDateMonth(),"YYYYMM"),
					provisionInfo.getNormalBalance().toPlainString(), 
					provisionInfo.getNormalRate().toPlainString(), 
					provisionInfo.getNormalReal().toPlainString(),
					provisionInfo.getFollowBalance().toPlainString(),
					provisionInfo.getFollowRate().toPlainString(), 
					provisionInfo.getFollowReal().toPlainString(), 
					provisionInfo.getMinorBalance().toPlainString(),
					provisionInfo.getMinorRate().toPlainString(),
					provisionInfo.getMinorReal().toPlainString(), 
					provisionInfo.getSuspiciousBalance().toPlainString(),
					provisionInfo.getSuspiciousRate().toPlainString(), 
					provisionInfo.getSuspiciousReal().toPlainString(),
					provisionInfo.getLossBalance().toPlainString(), 
					provisionInfo.getLossRate().toPlainString(), 
					provisionInfo.getLossReal().toPlainString());
			provisionInfo.setPushStatus(PushStatus.INPROSESS);
		}else {
			provisionInfoModel = new ProvisionInfoRequestModel(roundStr,provisionInfo.getReqId()) ;
			provisionInfo.setPushStatus(PushStatus.DELETEING);
		}
		SynResponseModel responseModel = financeService.provisionInfoFacade(provisionInfoModel,type);
		if (responseModel.isSuccess()) {
			provisionInfoDAO.save(provisionInfo);
		}else {
			throw  new RuntimeException();
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel settleInfoApi(String settleInfoId,
			IdentifierType type) {
		SettledInfo settle = settledInfoDAO.findOne(settleInfoId);
		if (settle == null) {
			log.info("未获取到放款相关数据settleInfoId={}",settleInfoId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();
		SettledInfoRequestModel infoRequestModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)){
			infoRequestModel = new SettledInfoRequestModel(roundStr,
										settle.getReqId(),
										contractDAO.findOne(settle.getLoanContractId()).getContractNumber(),
										settle.getMoney().toPlainString(), 
										DateUtil.getDateStr(settle.getLoanTime(),JSON.DEFFAULT_DATE_FORMAT), 
										DateUtil.getSimpleDate(settle.getStartDate()), 
										DateUtil.getSimpleDate(settle.getEndDate()));
			settle.setPushStatus(PushStatus.INPROSESS);
		}else {
			infoRequestModel = new SettledInfoRequestModel(roundStr, settle.getReqId());
			settle.setPushStatus(PushStatus.DELETEING);
		}
		
		SynResponseModel responseModel = financeService.settleInfoFacade(infoRequestModel, type);
		if (responseModel.isSuccess()) {
			settledInfoDAO.save(settle);
		}else{
			throw  new RuntimeException();
		}
		
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel financeInfoApi(String financeInfoId,IdentifierType type) {
		
		Capital capital = capitalDAO.findOne(financeInfoId);
		if (capital ==null) {
			log.info("未获取到融资信息相关数据financeInfoId={}",financeInfoId);
			  return null;
		  }
		String roundStr =  RandomUtil.random8Len();
		
		List<FinanceRepayPlanRequest> financeRepayPlanRequests = null;
		
		List<FinanceRepayPlan> financeRepayPlanList = financeRepayPlanDAO.listByFinanceId(financeInfoId);
		if (financeRepayPlanList != null && !financeRepayPlanList.isEmpty()) {
			financeRepayPlanRequests =  new ArrayList<FinanceRepayPlanRequest>();
			for (FinanceRepayPlan financeRepayPlan : financeRepayPlanList) {
				financeRepayPlanRequests.add(new FinanceRepayPlanRequest( financeRepayPlan.getInterest().toPlainString(), DateUtil.getSimpleDate(financeRepayPlan.getEndDate()), financeRepayPlan.getMoney().toPlainString()));
			}
		}
		
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

	@Override
	@Transactional
	public void queryTranInfoApi(String id , Cmd cmd ,String reqId, PushStatus pushStatus) {
		QueryInfoRequestModel infoRequestModel = null;
		String api="";
		if (PushStatus.DELETEING.equals(pushStatus)) {
			api = "delete/"+cmd.getKey();
		}else if (PushStatus.INPROSESS.equals(pushStatus)) {
			api = "post/"+cmd.getKey();
		}
		infoRequestModel = new QueryInfoRequestModel(reqId, api);
		SynResponseModel synResponseModel = financeService.queryInfoFacade(infoRequestModel);
		if (!synResponseModel.isSuccess()) {
			return;
		}
		
		switch (cmd) {
			case contractInfo:
				if (PushStatus.DELETEING.equals(pushStatus)) {
					contractDAO.delete(id);
					contractZywDAO.deleteByLoanContractId(id);
					contractDywDAO.deleteByLoanContractId(id);
					guaranteeDAO.deleteByLoanContractId(id);
					repayPlanDAO.deleteByLoanContractId(id);
					
				} else if (PushStatus.INPROSESS.equals(pushStatus)) {
					Contract contract = contractDAO.findOne(id);
					contract.setPushStatus(PushStatus.SUCCESS);
					contractDAO.save(contract);
				}
				break;
			case loanInfo:
				if (PushStatus.DELETEING.equals(pushStatus)) {
					settledInfoDAO.delete(id);
				} else if (PushStatus.INPROSESS.equals(pushStatus)) {
					SettledInfo settle = settledInfoDAO.findOne(id);
					settle.setPushStatus(PushStatus.SUCCESS);
					settledInfoDAO.save(settle);
				}
				break;
			case repay:
				if (PushStatus.DELETEING.equals(pushStatus)) {
					settledInfoDAO.delete(id);
				} else if (PushStatus.INPROSESS.equals(pushStatus)) {
					RepayInfo repay = repayInfoDAO.findOne(id);
					repay.setPushStatus(PushStatus.SUCCESS);
					repayInfoDAO.save(repay);
				}
				break;
			case baddebt:
				if (PushStatus.DELETEING.equals(pushStatus)) {
					otherBaddebtDAO.delete(id);
				} else if (PushStatus.INPROSESS.equals(pushStatus)) {
					OtherBaddebt otherBaddebt = otherBaddebtDAO.findOne(id);
					otherBaddebt.setPushStatus(PushStatus.SUCCESS);
					otherBaddebtDAO.save(otherBaddebt);
				}
				break;
			case pushCompanyEmployeeData:
				if (PushStatus.INPROSESS.equals(pushStatus)) {
					Employee employee = employeeDAO.findOne(id);
					employee.setPushStatus(PushStatus.SUCCESS);
					employeeDAO.save(employee);
				}
				break;
			case singleFinance:
				if (PushStatus.INPROSESS.equals(pushStatus)) {
					Capital capital = capitalDAO.findOne(id);
					capital.setPushStatus(PushStatus.SUCCESS);
					capitalDAO.save(capital);
				}
				break;
			default:
				break;
		}
		
	}

	@Override
	@Transactional
	public SynResponseModel assetTransferApi(String assetId, IdentifierType type) {
		
		AssetTransfer assetTransfer = assetTransferDAO.findOne(assetId);
		
		if (assetTransfer == null) {
			log.info("未获取资产转让信息相关数据assetId={}",assetId);
			return null;
		}
		
		String roundStr =  RandomUtil.random8Len();
		
		AssetTransferRequestModel assetTransferRequestModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)){
			assetTransferRequestModel = new AssetTransferRequestModel(roundStr,
					assetTransfer.getReqId(),
					contractDAO.findOne(assetTransfer.getLoanContractId()).getContractNumber(),
					assetTransfer.getTransferNumber(),
					assetTransfer.getTransferMoney().toPlainString(), 
					assetTransfer.getOriginalMoney().toPlainString(), 
					assetTransfer.getDiscountMoney().toPlainString(), 
					assetTransfer.getAcceptUnit(),
					assetTransfer.getProtocol(), 
					DateUtil.getSimpleDate(assetTransfer.getTransferDate()));
			assetTransfer.setPushStatus(PushStatus.INPROSESS);
		}else {
			assetTransferRequestModel = new AssetTransferRequestModel(roundStr,assetTransfer.getReqId());
			assetTransfer.setPushStatus(PushStatus.DELETEING);
			
		}
		SynResponseModel responseModel = financeService.assetTransferFacade(assetTransferRequestModel, type);
		if (responseModel.isSuccess()) {
			assetTransferDAO.save(assetTransfer);
		}else{
			throw  new RuntimeException();
		}
		
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel financePaymentApi(String paymentId,IdentifierType type) {
		RepaymentFinance repaymentFinance = repaymentFinanceDao.findOne(paymentId);
		if (repaymentFinance == null) {
			log.info("未获取融资实际还款信息相关数据paymentId={}",paymentId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();

		RepaymentFinanceRequestModel financeRequestModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)){
			financeRequestModel = new RepaymentFinanceRequestModel(roundStr,
					repaymentFinance.getReqId(), 
					repaymentFinance.getFinanceNumber(),
					DateUtil.getSimpleDate(repaymentFinance.getRepayDate()), 
					repaymentFinance.getMoney().toPlainString(), 
					repaymentFinance.getInterest().toPlainString(),
					repaymentFinance.getCharges().toPlainString());
			repaymentFinance.setPushStatus(PushStatus.INPROSESS);
			
		}else {
			financeRequestModel = new RepaymentFinanceRequestModel(roundStr,
					repaymentFinance.getReqId());
			repaymentFinance.setPushStatus(PushStatus.DELETEING);
		}
		
		
		SynResponseModel responseModel = financeService.financePaymentFacade(financeRequestModel, type);
		if (responseModel.isSuccess()) {
			repaymentFinanceDao.save(repaymentFinance);
		}else{
			throw  new RuntimeException();
		}
		
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel financeMonthBalanceApi(String financeMonthId,IdentifierType type) {
		
		FinanceMonthBalance financeMonthBalance = financeMonthBalanceDAO.findOne(financeMonthId);
		
		if (financeMonthBalance == null) {
			log.info("未获月度融资余额信息相关数据financeMonthId={}",financeMonthId);
			return null;
		}
		String roundStr =  RandomUtil.random8Len();
		FinanceMonthBalanceRequest financeMonthBalanceRequest = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)){
			financeMonthBalanceRequest = new FinanceMonthBalanceRequest(roundStr, financeMonthBalance.getReqId(),
					financeMonthBalance.getFinanceMonth(), 
					financeMonthBalance.getBalance().toPlainString());
			financeMonthBalance.setPushStatus(PushStatus.INPROSESS);
		}else {
			financeMonthBalanceRequest = new FinanceMonthBalanceRequest(roundStr, financeMonthBalance.getReqId());
			financeMonthBalance.setPushStatus(PushStatus.DELETEING);
		}
		
		SynResponseModel responseModel = financeService.financeMonthBalanceFacade(financeMonthBalanceRequest, type);
		if (responseModel.isSuccess()) {
			financeMonthBalanceDAO.save(financeMonthBalance);
		}else{
			throw  new RuntimeException();
		}
		
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel branchAccountInfoApi(String branchAccountInfoId,IdentifierType type) {
		
	   BranchAccountInfo branchAccountInfo = branchAccountInfoDAO.findOne(branchAccountInfoId);
		
		if (branchAccountInfo == null) {
			log.info("未获机构银行账户信息相关数据branchAccountInfoId={}",branchAccountInfoId);
			return null;
		}
		
		String roundStr =  RandomUtil.random8Len();
		
		BranchAccountInfoRequest branchAccountInfoRequest = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			branchAccountInfoRequest = new BranchAccountInfoRequest(roundStr,
					branchAccountInfo.getReqId(),
					branchAccountInfo.getBank(),
					branchAccountInfo.getAccount(),
					DTOUtils.getEnumIntVal(branchAccountInfo.getType()),
					branchAccountInfo.getAccountStatus().name(),
					DateUtil.getSimpleDate(branchAccountInfo.getOpenTime()));
			branchAccountInfo.setPushStatus(PushStatus.DELETEING);
		}else {
			branchAccountInfoRequest = new BranchAccountInfoRequest(roundStr,branchAccountInfo.getReqId());
			branchAccountInfo.setPushStatus(PushStatus.DELETEING);
		}
		SynResponseModel responseModel = financeService.branchAccountInfoFacade(branchAccountInfoRequest, type);
		if (responseModel.isSuccess()) {
			branchAccountInfoDAO.save(branchAccountInfo);
		}else{
			throw  new RuntimeException();
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel branchAccountBalanceApi(String branchAccountBalanceId, IdentifierType type) {
	
		BranchAccountBalance branchAccountBalance = branchAccountBalanceDAO.findOne(branchAccountBalanceId);
		
		if (branchAccountBalance == null) {
			log.info("未获机构银行账户月度余额信息相关数据branchAccountBalanceId={}",branchAccountBalanceId);
			return null;
		}
		
		String roundStr =  RandomUtil.random8Len();
		
		BranchAccountBalanceRequest branchAccountBalanceRequest = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			branchAccountBalanceRequest = new BranchAccountBalanceRequest(roundStr,
					branchAccountBalance.getReqId(), 
					branchAccountBalance.getDateMonth(), 
					branchAccountBalance.getAccount(), 
					branchAccountBalance.getMoney().toPlainString());
			branchAccountBalance.setPushStatus(PushStatus.INPROSESS);
		}else {
			branchAccountBalanceRequest = new BranchAccountBalanceRequest(roundStr,branchAccountBalance.getReqId());
		
			branchAccountBalance.setPushStatus(PushStatus.DELETEING);
		}
		
		SynResponseModel responseModel = financeService.branchAccountBalanceFacade(branchAccountBalanceRequest, type);
		if (responseModel.isSuccess()) {
			branchAccountBalanceDAO.save(branchAccountBalance);
		}else{
			throw  new RuntimeException();
		}
		return responseModel;
	}

	@Override
	@Transactional
	public SynResponseModel dayEndDzApi(String dayEndDzId, IdentifierType type) {
		DayEndDz dayEndDz = dayEndDzDAO.findOne(dayEndDzId);
		if (dayEndDz == null) {
			log.info("未获日结数据对账相关数据dayEndDzId={}",dayEndDzId);
			return null;
		}
         String roundStr =  RandomUtil.random8Len();
		
		DayEndDzRequestModel dayEndDzRequestModel = null;
		if (IdentifierType.A.equals(type) || IdentifierType.U.equals(type)) {
			dayEndDzRequestModel = new DayEndDzRequestModel(roundStr,
					dayEndDz.getReqId(), 
					DateUtil.getSimpleDate(dayEndDz.getCheckDate()),
					dayEndDz.getDayCount(),
					dayEndDz.getDayMoney().toPlainString(), 
					dayEndDz.getDayLoan().toPlainString(), 
					dayEndDz.getDayRepay().toPlainString(),
					dayEndDz.getLoanBalance().toPlainString(), 
					dayEndDz.getLoanMoney().toPlainString(), 
					dayEndDz.getLoanCount());
			dayEndDz.setPushStatus(PushStatus.DELETEING);
		}else {
			dayEndDzRequestModel = new DayEndDzRequestModel(roundStr,dayEndDz.getReqId());
			dayEndDz.setPushStatus(PushStatus.DELETEING);
		}
		
		SynResponseModel responseModel = financeService.dayEndDzApi(dayEndDzRequestModel, type);
		if (responseModel.isSuccess()) {
			dayEndDzDAO.save(dayEndDz);
		}else{
			throw  new RuntimeException();
		}
		return responseModel;
	}
	
	

}
