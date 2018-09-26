package com.kingtech.web.commons.base.utils;

import java.util.ArrayList;
import java.util.List;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.ContractDyw;
import com.kingtech.dao.entity.ContractZyw;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.model.ContractDywModel;
import com.kingtech.model.ContractZywModel;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.RepayPlanModel;
import com.kingtech.model.SettledInfoModel;

public class DTOUtils {
	
	public static PersonalCustomerModel getPersonalCustomerModel(PersonalCustomer personalCustomer){
		PersonalCustomerModel customerModel = null;
		if (personalCustomer != null) {
			customerModel = new PersonalCustomerModel(personalCustomer.getId(),personalCustomer.getName(),
					personalCustomer.getSex(),
					personalCustomer.getCardType(),
					personalCustomer.getCardNumber(),
					personalCustomer.getPhone(),
					personalCustomer.getIsFarmer(),
					personalCustomer.getEducation(),
					personalCustomer.getEmail(),
					personalCustomer.getIsMarry(),
					personalCustomer.getNationality(),
					personalCustomer.getBirthDate(),
					personalCustomer.getNation(),
					personalCustomer.getAddress(),
					personalCustomer.getPostCode(),
					personalCustomer.getRegisteredAddress(),
					personalCustomer.getNativePlace(),
					personalCustomer.getWorkUnit(),
					personalCustomer.getPosition());
		}
		
		return customerModel;
	}
	
	public static EnterpriseCustomerModel getEnterpriseCustomerModel(EnterpriseCustomer enterpriseCustomer){
		EnterpriseCustomerModel enterpriseCustomerModel = null;
		if (enterpriseCustomer != null ) {
			enterpriseCustomerModel = new EnterpriseCustomerModel(enterpriseCustomer.getId(),
																enterpriseCustomer.getName(),
																enterpriseCustomer.getScale(),
																enterpriseCustomer.getIndustryType(),
																enterpriseCustomer.getIndustryinvolved(),
																enterpriseCustomer.getOrganizationcode(),
																enterpriseCustomer.getRegistCode(),
																enterpriseCustomer.getRegistOffice(),
																enterpriseCustomer.getRegistDate(),
																enterpriseCustomer.getNationalTaxCode(),
																enterpriseCustomer.getLandTaxCode(),
																enterpriseCustomer.getLicenseCode(),
																enterpriseCustomer.getLicenceEndDate(),
																enterpriseCustomer.getLegalPerson(),
																enterpriseCustomer.getFoundDate(),
																enterpriseCustomer.getController(),
																enterpriseCustomer.getReallyCapital(),
																enterpriseCustomer.getBusinessScope(),
																enterpriseCustomer.getRegisterAddress(),
																enterpriseCustomer.getAddress(),
																enterpriseCustomer.getPhone(),
																enterpriseCustomer.getLinkman(),
																enterpriseCustomer.getEmail(),
																enterpriseCustomer.getWebSite());
		}
		
		return enterpriseCustomerModel;
		
	}
	
	
	public static  List<GuaranteeModel> getGuaranteeModels(List<Guarantee> guarantees){
		List<GuaranteeModel>  guaranteeModels = new ArrayList<GuaranteeModel>();
			for (Guarantee guarantee :guarantees) {
					guaranteeModels.add(new GuaranteeModel(guarantee.getName(),
														   guarantee.getCardType(), 
														   guarantee.getCardNumber(),
														   guarantee.getAddress()));
			}
		return  guaranteeModels;
	}
	
	
	public static List<ContractZywModel> getZywModels(List<ContractZyw> collaterals){
		 List<ContractZywModel> collateralModels = new ArrayList<ContractZywModel>();
			 for (ContractZyw collateral :collaterals) {
				 collateralModels.add(new ContractZywModel(collateral.getPledgeType(),
						  collateral.getName(), 
						  collateral.getWorth(), 
						  collateral.getAddress(),
						  collateral.getUnit()));
			}
		 return  collateralModels;
	}
	
	public static List<ContractDywModel> getDywModels(List<ContractDyw> collaterals){
		 List<ContractDywModel> collateralModels = new ArrayList<ContractDywModel>();
			 for (ContractDyw collateral :collaterals) {
				 collateralModels.add(new ContractDywModel(collateral.getPledgeType(),
						  collateral.getName(), 
						  collateral.getWorth(), 
						  collateral.getAddress(),
						  collateral.getUnit()));
			}
		 return  collateralModels;
	}
	
	
	public static List<RepayPlanModel> getRepayPlanModels(List<RepayPlan> repayPlans){
		
		List<RepayPlanModel>  planModels = new ArrayList<RepayPlanModel>();
		
		for (RepayPlan repayPlan : repayPlans) {
			planModels.add(new RepayPlanModel(DateUtil.getDateStr(
													repayPlan.getEndDate(), "yyyy-MM-dd"),
													repayPlan.getMoney(), repayPlan.getInterest()));
		}
		return planModels;
	}
	
	
	public static SettledInfoModel getSettledInfoModels (SettledInfo  settledInfo){
		SettledInfoModel infoModel =null;
		if (settledInfo !=null) {
			infoModel = new SettledInfoModel(null,settledInfo.getMoney(),
					DateUtil.getDateStr(settledInfo.getLoanTime(), "yyyy-MM-dd"), 
					DateUtil.getDateStr(settledInfo.getStartDate(), "yyyy-MM-dd"),
					DateUtil.getDateStr(settledInfo.getEndDate(), "yyyy-MM-dd"));
		}
		
		return infoModel;
	}
	
	
	
	public static String getNewStr(Enum<?> euEnum){
		if (euEnum ==null ) {
			return null;
		}
		return euEnum.name().replace("S_", "");
	}
	
	public static int getEnumIntVal(Enum<?> euEnum) {
		return Integer.valueOf(euEnum.name().replace("S_", ""));
	}
	

}
