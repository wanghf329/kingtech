package com.kingtech.web.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String legalRepresentative;
	
	private String regCapital;
	
	private Date buildDate;
	
	private Date openingDate;
	
	private String siteArea;
	
	private String businessAddr;
	
	private String organizationCode;
	
	private String licence;
	
	private String nationalRegNum;
	
	private String landRegNum;
	
	private String businessScope;

	public BranchModel(String legalRepresentative, String regCapital,
			Date buildDate, Date openingDate, String siteArea,
			String businessAddr, String organizationCode, String licence,
			String nationalRegNum, String landRegNum, String businessScope) {
		super();
		this.legalRepresentative = legalRepresentative;
		this.regCapital = regCapital;
		this.buildDate = buildDate;
		this.openingDate = openingDate;
		this.siteArea = siteArea;
		this.businessAddr = businessAddr;
		this.organizationCode = organizationCode;
		this.licence = licence;
		this.nationalRegNum = nationalRegNum;
		this.landRegNum = landRegNum;
		this.businessScope = businessScope;
	}
	
	

}
