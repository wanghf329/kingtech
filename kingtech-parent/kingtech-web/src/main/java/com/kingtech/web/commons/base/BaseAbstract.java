package com.kingtech.web.commons.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseAbstract {

	/**
	 * 获取请求数据
	 * @param obj
	 * @return
	 */

	public Map<String, String> getDataAndSign(BaseRequestModel obj) {

		String dataStr = JSON.toJSONString(obj); // 请求数据
		String dataSign = JSON.toJSONString(obj, Labels.includes("sign")); // 验签数据
		Map<String, String> dataMap = JSON.parseObject(dataStr, Map.class);
		Map<String, String> signMap = JSON.parseObject(dataSign, Map.class);

		String sign = SignUtils.getSignStr(signMap);
		dataMap.put("sign", sign);

		return dataMap;

	}
	/**
	 * 请求远程接口
	 * @param request
	 * @param suffixUrl
	 * @return
	 */

	public SynResponseModel getResponse(Map<String, String> request,
			String suffixUrl) {

		try {
			String response = HttpUtil.postFormResponse(BaseConfig.REQUEST_URL
					+ "/" + suffixUrl, request);
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
		String sign = SignUtils.getSignStr(signMap);
		return sign.equals(baseResponsModel.getSign());

	}

}
