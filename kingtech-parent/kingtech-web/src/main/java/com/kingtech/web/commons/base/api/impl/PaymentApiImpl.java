package com.kingtech.web.commons.base.api.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.common.utils.RandomUtil;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.CollateralDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
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
import com.kingtech.model.PersonalCustomerModel;
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
	public void contractInfoApi(String loanIdContractId, IdentifierType type) {
		
		Contract contract = contractDAO.findOne(loanIdContractId);
		if (contract == null) {
			log.info("未获取到相关数据loanIdContractId={}",loanIdContractId);
			return;
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
			return;
		}
		
		SynResponseModel responseModel = financeService.contractFacade(contractModel);
		if (responseModel.isSuccess()) {
			contract.setPushStatus(PushStatus.INPROSESS);
			contractDAO.save(contract);
		}
		
	}

	@Override
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

}
