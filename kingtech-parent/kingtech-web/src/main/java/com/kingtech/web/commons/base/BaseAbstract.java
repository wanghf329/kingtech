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
import com.kingtech.model.BaseRequestModel;
import com.kingtech.model.BaseResponsModel;
import com.kingtech.model.SynResponseModel;

@Slf4j
public class BaseAbstract {

	}
	/**
	 * 请求远程接口
	 * @param request
	 * @param suffixUrl
	 * @return
	 */

	public  SynResponseModel getResponse(BaseResponsModel baseResponsModel,String suffixUrl) {
		String dataSign = JSON.toJSONString(baseResponsModel, Labels.includes("sign")); // 验签数据
		Map<String, Object> signMap = JSON.parseObject(dataSign, Map.class);
		String sign = SignUtils.getSignStr(signMap);
		baseResponsModel.setSign(sign);
		String data = JSONObject.toJSONString(baseResponsModel);
		try {
			String response = HttpUtil.postJsonResponse(BaseConfig.REQUEST_URL	+ "/" + suffixUrl, data);
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
	
	public static void main(String[] args) {
		System.err.println(MD5.MD5Encode("client_id=8017612511286958&corporate_name=宝鸡小贷&identifier=U&land_reg_num=ASD156468541&legal_representative=tgt&licence=13254681451&national_reg_num=ASFADF1325456&organization_code=asdfas14564&reg_capital=1500.2&req_id=91310000775785552H38101&round_str=0000000001&token=1f892153f8931639fe264b49e3ee0f93&app_key=dst9vxn9231k5414"));
	}

}
