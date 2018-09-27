package com.kingtech.web.commons.base;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.Labels;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.HttpUtil;
import com.kingtech.common.utils.MD5;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.IdentifierType;
import com.kingtech.szsm.model.BaseRequestModel;
import com.kingtech.szsm.model.BaseResponsModel;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.SettledInfoRequestModel;
import com.kingtech.szsm.model.SynResponseModel;

@Slf4j
public class BaseAbstract {

	/**
	 * 请求远程接口
	 * @param request
	 * @param suffixUrl
	 * @return
	 */

	public  SynResponseModel getResponse(BaseRequestModel baseRequestModel,String suffixUrl,IdentifierType type) {
		String sign = getOtherSign(baseRequestModel);
		if (sign == null) {
			String dataSign = JSON.toJSONString(baseRequestModel, Labels.includes("sign")); // 验签数据
			Map<String, Object> signMap = JSON.parseObject(dataSign, Map.class);
			sign = SignUtils.getSignStr(signMap);
		}
	
		baseRequestModel.setSign(sign);
		String data = JSONObject.toJSONString(baseRequestModel);
		try {
			String response = null;
			switch (type) {
			case A:
				response = HttpUtil.postJsonResponse(BaseConfig.REQUEST_URL	+ "/" + suffixUrl, data);
				break;
			case U:
				response = HttpUtil.putJsonResponse(BaseConfig.REQUEST_URL	+ "/" + suffixUrl, data);
				break;
			case D:	
				response = HttpUtil.delJsonResponse(BaseConfig.REQUEST_URL	+ "/" + suffixUrl, data);
			default:
				break;
			}
				
				
			return JSON.parseObject(response, SynResponseModel.class);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 检验返回数据是否合法
	 * @param baseResponsModel
	 * @return
	 */
	public boolean verifyResponse(BaseResponsModel baseResponsModel) {
		String dataSign = JSON.toJSONString(baseResponsModel,Labels.includes("sign")); // 验签数据
		Map<String, String> signMap = JSON.parseObject(dataSign, Map.class);
		String sign = SignUtils.getReponseSignStr(signMap);
		return sign.equals(baseResponsModel.getSign());

	}
	
	
	public  String getOtherSign(BaseRequestModel baseRequestModel){
		StringBuilder builder = new StringBuilder();
		if (baseRequestModel instanceof ContractRequestModel) {
			ContractRequestModel contractRequestModel = (ContractRequestModel) baseRequestModel;
			
			builder.append("borrowerType=").append(contractRequestModel.getBorrowerType()).append("&");
			builder.append("clientId=").append(contractRequestModel.getClientId()).append("&");
			builder.append("business=").append(contractRequestModel.getBusiness()).append("&");
			builder.append("money=").append(contractRequestModel.getMoney()).append("&");
			builder.append("contractNumber=").append(contractRequestModel.getContractNumber()).append("&");
			builder.append("contractName=").append(contractRequestModel.getContractName()).append("&");
			builder.append("endDate=").append(contractRequestModel.getEndDate()).append("&");
			builder.append("startDate=").append(contractRequestModel.getStartDate()).append("&");
			builder.append("loanMethod=").append(contractRequestModel.getLoanMethod()).append("&");
			builder.append("repayMethod=").append(contractRequestModel.getRepayMethod()).append("&");
			builder.append("term=").append(contractRequestModel.getTerm()).append("&");
			builder.append("termType=").append(contractRequestModel.getTermType()).append("&");
			builder.append("purpose=").append(contractRequestModel.getPurpose()).append("&");
			builder.append("rate=").append(contractRequestModel.getRate()).append("&");
			builder.append("rateType=").append(contractRequestModel.getRateType()).append("&");
			builder.append("repaySource=").append(contractRequestModel.getRepaySource()).append("&");
			builder.append("reqId=").append(contractRequestModel.getReqId()).append("&");
			builder.append("roundStr=").append(contractRequestModel.getRoundStr()).append("&");
			builder.append("signTime=").append(contractRequestModel.getSignTime()).append("&");
			builder.append("token=").append(contractRequestModel.getToken()).append("&");
			builder.append("appKey=").append(BaseConfig.APPKEY);
			
		}else if (baseRequestModel instanceof SettledInfoRequestModel) {
			SettledInfoRequestModel settle = (SettledInfoRequestModel) baseRequestModel;
			builder.append("clientId=").append(settle.getClientId()).append("&");
			builder.append("reqId=").append(settle.getReqId()).append("&");
			builder.append("roundStr=").append(settle.getRoundStr()).append("&");
			builder.append("token=").append(settle.getToken()).append("&");
			builder.append("contractNumber=").append(settle.getContractNumber()).append("&");
			builder.append("money=").append(settle.getMoney()).append("&");
			builder.append("loanTime=").append(settle.getLoanTime()).append("&");
			builder.append("appKey=").append(BaseConfig.APPKEY);
			
		}
		
		if (builder.length() > 0) {
			String result = builder.toString();
			  log.info("计算验证签名的原始数据result={}",result);
		      return MD5.MD5Encode(result);
		}
		return null;
	}
	
}
