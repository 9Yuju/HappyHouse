package com.ssafy.happyhouse.model;

public class InterestAreaDto {
	private String id;
	private String dongCode;
	private String juso;
	
	public String getJuso() {
		return juso;
	}

	public void setJuso(String juso) {
		this.juso = juso;
	}

	public InterestAreaDto() {
	}

	public InterestAreaDto(String id, String dongCode) {
		this.id = id;
		this.dongCode = dongCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	@Override
	public String toString() {
		return "interestArea [id=" + id + ", dongCode=" + dongCode + "]";
	}
}
