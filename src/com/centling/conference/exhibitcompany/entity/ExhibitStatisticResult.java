package com.centling.conference.exhibitcompany.entity;

/**
 * 展商管理统计结果的实体，包含名字，总计数量，预定数量
 * 例子：展位统计，每类展位，总共有多少，预定了多少；额外的家具，每类名称，总共有多少？预订了多少？
 * @author centling
 *
 */
public class ExhibitStatisticResult {
	/**
	 * id
	 */
	private String id;
	/**
	 * 名字
	 */
	private String resultName;
	
	/**
	 * 总数
	 */
	private int resultAllNum;
	
	/**
	 * 租用数量或者预定数量
	 */
	private int resultRentNum;
	

	public ExhibitStatisticResult() {
		super();
	}
	
	public ExhibitStatisticResult(String id, String resultName,
			int resultAllNum, int resultRentNum) {
		super();
		this.id = id;
		this.resultName = resultName;
		this.resultAllNum = resultAllNum;
		this.resultRentNum = resultRentNum;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResultName() {
		return resultName;
	}


	public void setResultName(String resultName) {
		this.resultName = resultName;
	}


	public int getResultAllNum() {
		return resultAllNum;
	}


	public void setResultAllNum(int resultAllNum) {
		this.resultAllNum = resultAllNum;
	}


	public int getResultRentNum() {
		return resultRentNum;
	}


	public void setResultRentNum(int resultRentNum) {
		this.resultRentNum = resultRentNum;
	}
	
	
}
