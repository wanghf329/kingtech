package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.BranchAccountInfo;
import com.kingtech.dao.rdbms.BranchAccountInfoDAO;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.BranchAccountInfoModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.BranchAccountInfoService;
@Service
public class BranchAccountInfoServiceImpl implements BranchAccountInfoService{
	
	@Autowired
	private BranchAccountInfoDAO branchAccountInfoDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTBRANCHACCOUNTLISTSQL = "SELECT * from TB_BRANCH_ACCOUNT_INFO t where t.RECORD_STATUS ='NORMAL'  order by t.CREATE_TIME DESC";

	@Override
	public BranchAccountInfo addNew(BranchAccountInfoModel model) {
		BranchAccountInfo branchAccountInfo = null;
		try {
			IdentifierType type = null;
			if (StringUtils.isEmpty(model.getId())) {
				branchAccountInfo = new BranchAccountInfo();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(branchAccountInfo, model);
				type = IdentifierType.A;
			} else {
				branchAccountInfo = branchAccountInfoDao.findOne(model.getId());
				String reqId = branchAccountInfo.getReqId();
				BeanUtils.copyProperties(branchAccountInfo, model);
				branchAccountInfo.setReqId(reqId);
				branchAccountInfo.setPushStatus(PushStatus.INITATION);
				type = IdentifierType.U;
			}
			branchAccountInfo.setRecordStatus(RecordStatus.NORMAL);
			branchAccountInfo = branchAccountInfoDao.save(branchAccountInfo);
			paymentApi.branchAccountInfoApi(branchAccountInfo.getId(), type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchAccountInfo;
	}

	@Override
	public List<BranchAccountInfo> listAll() {
		return (List)branchAccountInfoDao.findAll();
	}

	@Override
	public BranchAccountInfo getById(String id) {
		return branchAccountInfoDao.findOne(id);
	}

	@Override
	public PagedResult<BranchAccountInfo> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<BranchAccountInfo> list = dq.nativeQueryPagingList(BranchAccountInfo.class, pageAble, LISTBRANCHACCOUNTLISTSQL, params);
		Long count = dq.nativeQueryCount(LISTBRANCHACCOUNTLISTSQL, params);
		return new PagedResult(list,count);
	}

	@Override
	public List<BranchAccountInfo> listAccountInfoByStatus(
			List<PushStatus> pushStatus) {
		return branchAccountInfoDao.listBypushStatus(pushStatus);
	}

	@Override
	public void syncAccountInfoPushStatus() {
		branchAccountInfoDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS)).forEach(s->{
			paymentApi.queryTranInfoApi(s.getId(), Cmd.account, s.getReqId(),s.getPushStatus());
		});
		
	}
	
	
	

}
