package com.kingtech.web.commons.base.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.Collateral;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.model.CollateralModel;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.RepayPlanModel;
import com.kingtech.model.SettledInfoModel;

public class DTOUtils {
	
	public static PersonalCustomerModel getPersonalCustomerModel(PersonalCustomer personalCustomer){
		PersonalCustomerModel customerModel = null;
		if (personalCustomer != null) {
			customerModel = new PersonalCustomerModel(personalCustomer.getName(),
					                                  personalCustomer.getSex().getKey(),
					                                  getNewStr(personalCustomer.getCategory()),
					                                  personalCustomer.getCardNum(),
					                                  personalCustomer.getPhone(), 
					                                  getNewStr(personalCustomer.getFarmersFlag()),
					                                  personalCustomer.getEducation(), 
					                                  personalCustomer.getFax(), 
					                                  personalCustomer.getEmail(), 
					                                  personalCustomer.getMarriage(), 
					                                  personalCustomer.getNationality(),
					                                  DateUtil.getDateStr(personalCustomer.getBirthDate(), "yyyy-MM-dd"),
					                                  personalCustomer.getNation(), 
					                                  personalCustomer.getAddressProvince(), 
					                                  personalCustomer.getAddressCity(), 
					                                  personalCustomer.getAddressDistrict(), 
					                                  personalCustomer.getAddress(), 
					                                  personalCustomer.getPostCode(), 
					                                  personalCustomer.getResidence(), 
					                                  personalCustomer.getNativePlace(), 
					                                  personalCustomer.getWorkUnit(), 
					                                  personalCustomer.getPost());
		}
		
		return customerModel;
	}
	
	public static EnterpriseCustomerModel getEnterpriseCustomerModel(EnterpriseCustomer enterpriseCustomer){
		EnterpriseCustomerModel enterpriseCustomerModel = null;
		if (enterpriseCustomer != null ) {
			enterpriseCustomerModel = new EnterpriseCustomerModel(enterpriseCustomer.getCorporateName(),
					                                              getNewStr(enterpriseCustomer.getScale()),
					                                              getNewStr(enterpriseCustomer.getIndustryType()),
					                                              getNewStr(enterpriseCustomer.getIndustryinvolved()),
					                                              enterpriseCustomer.getOrganizationcode(),
					                                              enterpriseCustomer.getRegCode(),
					                                              enterpriseCustomer.getRegOffice(),
					                                              DateUtil.getDateStr(enterpriseCustomer.getRegDate(), "yyyy-MM-dd"),
					                                              enterpriseCustomer.getNationalregNum(), 
					                                              enterpriseCustomer.getLandRegNum(), 
					                                              enterpriseCustomer.getLicence(), 
					                                              DateUtil.getDateStr(enterpriseCustomer.getLicenceEndDate(),"yyyy-MM-dd"), 
					                                              enterpriseCustomer.getNature(),
					                                              enterpriseCustomer.getEmployNum()+"",
					                                              enterpriseCustomer.getLegalRepresentative(), 
					                                              DateUtil.getDateStr(enterpriseCustomer.getBulidDate(),"yyyy-MM-dd"),
					                                              enterpriseCustomer.getActualController(),
					                                              enterpriseCustomer.getRegCapital().toPlainString(),
					                                              enterpriseCustomer.getReallyCapital().toPlainString(),
					                                              enterpriseCustomer.getBusinessScope(),
					                                              enterpriseCustomer.getRegAddress(), 
					                                              enterpriseCustomer.getContactAddressProvince(),
					                                              enterpriseCustomer.getContactAddresscity(),
					                                              enterpriseCustomer.getContactAddressDistrict(),
					                                              enterpriseCustomer.getContactAddress(),
					                                              enterpriseCustomer.getPostcode(),
					                                              enterpriseCustomer.getPhone(), 
					                                              enterpriseCustomer.getLinkman(),
					                                              enterpriseCustomer.getFax(), 
					                                              enterpriseCustomer.getEmail(),
					                                              enterpriseCustomer.getWebSite());
			
		}
		
		return enterpriseCustomerModel;
		
	}
	
	
	public static  List<GuaranteeModel> getGuaranteeModels(List<Guarantee> guarantees){
		List<GuaranteeModel>  guaranteeModels = new ArrayList<GuaranteeModel>();
			for (Guarantee guarantee :guarantees) {
				guaranteeModels.add(new GuaranteeModel(guarantee.getName(), guarantee.getCardNum(), guarantee.getPhone(), guarantee.getAddress()));
			}
		
		return  guaranteeModels;
	}
	
	
	public static List<CollateralModel> getCollateralModels(List<Collateral> collaterals){
		
		 List<CollateralModel> collateralModels = new ArrayList<CollateralModel>();
			 for (Collateral collateral :collaterals) {
				 collateralModels.add(new CollateralModel(collateral.getPledgeType().getKey(),
						  getNewStr(collateral.getCollateralType()),
						  collateral.getCollateralName(), 
						  collateral.getWarrantNum(), 
						  collateral.getEvaluationValue() == null ? null : collateral.getEvaluationValue().toPlainString(),
						  collateral.getWarrantHolder(),
						  collateral.getCollateralAddr(),
						  DateUtil.getDateStr(collateral.getHandleDate(), "yyyy-MM-dd")));
			}
			
		 
		 return  collateralModels;
	}
	
	
	public static List<RepayPlanModel> getRepayPlanModels(List<RepayPlan> repayPlans){
		
		List<RepayPlanModel>  planModels = new ArrayList<RepayPlanModel>();
		
		for (RepayPlan repayPlan : repayPlans) {
			planModels.add(new RepayPlanModel(DateUtil.getDateStr(repayPlan.getRepayDate(), "yyyy-MM-dd"),
					repayPlan.getPrincipal().toPlainString(), repayPlan.getInterest().toPlainString()));
		}
		return planModels;
	}
	
	
	public static SettledInfoModel getSettledInfoModels (SettledInfo  settledInfo){
		SettledInfoModel infoModel =null;
		if (settledInfo !=null) {
			infoModel = new SettledInfoModel(settledInfo.getMoney().toPlainString(),
					DateUtil.getDateStr(settledInfo.getLoanDate(), "yyyy-MM-dd"), 
					DateUtil.getDateStr(settledInfo.getDebtStartDate(), "yyyy-MM-dd"),
					DateUtil.getDateStr(settledInfo.getDebtEndDate(), "yyyy-MM-dd"));
		}
		
		return infoModel;
	}
	
	
	
	public static String getNewStr(Enum<?> euEnum){
		if (euEnum ==null ) {
			return null;
		}
		return euEnum.name().replace("S_", "");
	}
	

}
