package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.RepayInfoService;

@Slf4j
@Service
public class RepayInfoServiceImpl implements RepayInfoService{
	
	@Autowired
	private RepayInfoDAO repayInfoDao;
	
	@Autowired
	private DynamicQuery dq;

	@Override
	public PagedResult<ModelExt> pageList(Pageable pageAble) {
		String sql = "SELECT t1.ID,t1.LOAN_CONTRACT_ID,t1.REPAY_DATE,t1.REPAY_AMOUNT,t1.REPAY_PRINCIPAL_AMOUNT,t1.REPAY_INTEREST_AMOUNT,t2.LOAN_CONTRACT_NO,t2.LOAN_CONTRACT_NAME,t1.PUSH_STATUS "
				+ "		FROM TB_LOAN_REPAY_INFO t1,TB_LOAN_CONTRACT t2 "
				+ "   WHERE t1.LOAN_CONTRACT_ID = t2.ID ORDER BY t1.LOAN_CONTRACT_ID,t1.REPAY_DATE DESC ";
		
		String[] params = new String[0];
		List<Object[]> list = dq.nativeQueryPagingList(Object[].class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<ModelExt> result = new ArrayList<ModelExt>();
		for (Object[] obj : list) {
			result.add(new ModelExt(
					   new RepayInfoModel((String)obj[0], 
							   			  (String)obj[1],
							   			((BigDecimal)obj[3]).toPlainString(),
							   			((BigDecimal)obj[4]).toPlainString(),
							   			((BigDecimal)obj[5]).toPlainString(),
							      DateUtil.getDateStr((Date)obj[2], "yyyy-MM-dd")),
							      (String)obj[6],
							      (String)obj[7],
							      PushStatus.valueOf((String)obj[8])));
		}
		return new PagedResult(result,count);
	}
}