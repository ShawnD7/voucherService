package com.iwantunlimited.model;

import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VoucherModel {
	@Id
	private String id;
	private String code;
	private boolean used;
	private Date createdOn;
	private Date expiresOn;
	private double amount;
	

	public static VoucherModel of(String code, double amount) {
		VoucherModel model = new VoucherModel();
		model.setCode(code);
		model.setCreatedOn(new Date());
		model.setAmount(amount);
		long currentTime = System.currentTimeMillis();

		Calendar timer = Calendar.getInstance();
		timer.add(Calendar.DAY_OF_MONTH,30);
		model.setExpiresOn(timer.getTime());
		return model;
	}

	public VoucherModel() {
		super();
	}

	public VoucherModel(String code, double amount) {
		this.code = code;
		this.createdOn = new Date();
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

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
