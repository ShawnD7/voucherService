package com.iwantunlimited.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VoucherModel {
	@Id
	private String id;
	private String code;
	private Boolean user;
//	private String expriesOn;
	private Date date;
	private int amount = 300;
	
	
	public VoucherModel() {
		super();
	}

	public VoucherModel(String id, String code, Boolean user, Date date, int amount) {
		super();
		this.id = id;
		this.code = code;
		this.user = user;
		this.date = date;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getUser() {
		return user;
	}

	public void setUser(Boolean user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "VoucherModel [id=" + id + ", code=" + code + ", user=" + user + ", discountAmount="
				+ amount + "]";
	}
	
}
