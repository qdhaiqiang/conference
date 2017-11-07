package com.centling.conference.base.page;

import java.util.List;

public class Pagination {
	
	private List<Object> rows;
	private String total;
	
	public List  getRows() {
		return rows;
	}
	public void setRows(List  rows) {
		this.rows = rows;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
			

}
