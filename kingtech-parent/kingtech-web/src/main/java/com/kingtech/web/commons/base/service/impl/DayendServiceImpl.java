package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.DayEndDz;
import com.kingtech.dao.rdbms.DayEndDzDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.DayEndDzModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.DayEndService;

@Service
public class DayendServiceImpl implements DayEndService{
	
	@Autowired
	private DynamicQuery dq;
	
	@Autowired
	private DayEndDzDAO dayEndDzDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi api;

	@Override
	public PagedResult<DayEndDzModel> pageListDayEndInfo(Pageable pageAble) {
		String sql = " SELECT * from TB_DAYEND_DZ t order by t.CREATE_TIME DESC ";
		
		String[] params = new String[0];
		List<DayEndDz> list = dq.nativeQueryPagingList(DayEndDz.class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<DayEndDzModel> result = new ArrayList<DayEndDzModel>();
		for(DayEndDz c : list){
			result.add(new DayEndDzModel(c.getPushStatus(), c.getCheckDate(), c.getDayCount(), c.getDayMoney(), c.getDayLoan(), c.getDayRepay(), c.getLoanBalance(), c.getLoanMoney(), c.getLoanCount()));
		}
		return new PagedResult(result,count);
	}

	@Override
	public void save(DayEndDzModel model) {
		DayEndDz dz = dayEndDzDao.save(new DayEndDz(creatRequstId.getReqId(),PushStatus.INITATION,RecordStatus.NORMAL,
				model.getCheckDate(),model.getDayCount(),model.getDayMoney(),model.getDayLoan(),model.getDayRepay(),
				model.getLoanBalance(),model.getLoanMoney(),model.getLoanCount()));
		api.dayEndDzApi(dz.getId(), IdentifierType.A);
	}

	@Override
	public void syncDayendPushStatus() {
		dayEndDzDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS,PushStatus.DELETEING)).forEach(s->{
			api.queryTranInfoApi(s.getId(), Cmd.dayEndReport, s.getReqId(),s.getPushStatus());
		});
	}
}
