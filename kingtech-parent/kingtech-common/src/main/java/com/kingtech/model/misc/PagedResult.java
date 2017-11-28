/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingtech.model.misc;

import java.util.List;

import com.kingtech.model.BaseRequestModel;

/**
 *
 * @author whf
 */
public class PagedResult<T> extends BaseRequestModel {

	private static final long rserialVersionUID = 20131015L;

	private List<T> results;

	private long totalSize;

	public PagedResult() {
	}

	public PagedResult(List<T> results, long totalSize) {
		this.results = results;
		this.totalSize = totalSize;
	}

	public List<T> getResults() {
		return results;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
