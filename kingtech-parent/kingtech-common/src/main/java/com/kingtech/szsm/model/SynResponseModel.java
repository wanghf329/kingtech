package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 同步返回结果
 * @author baijt
 *
 */
@NoArgsConstructor
public class SynResponseModel   extends BaseResponsModel{
	
	public SynResponseModel ( String resultCode,String resultMsg){
		super(resultCode, resultMsg);
	}
	
}
