package com.iwantunlimited.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.iwantunlimited.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.iwantunlimited.model.VoucherModel;

@Component
public class VoucherService {
	private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);

	@Autowired
	protected MongoTemplate mongoService;
	
	private static final Class<VoucherModel> model = VoucherModel.class;
	

	
	public Date setDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
		
	public List<VoucherModel> create(int number, double amount) {
		List<VoucherModel> response = new ArrayList<>();

		for(int i = 1; i <= number; i++) {
			String code = StringHelper.createRandomCode(8);
			VoucherModel model = VoucherModel.of(code,amount);
			model = mongoService.insert(model);
			response.add(model);
			logger.trace("[{}-Voucher Created] id:{}, amount:{}, createdOn:{},expiry:{}",response.size(),model.getId(),model.getAmount(),model.getCreatedOn(),model.getExpiresOn());
		}
		logger.info("[Vouchers Generated] Qty:{}",response.size());
		return response;
	}
	public VoucherModel findByCode(String code) throws NotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		VoucherModel selected = mongoService.findOne(query, model);
		if (selected == null) {
			throw new NotFoundException();
		}
		return selected;
	}


	public VoucherModel redeem(String code) throws NotFoundException {

		VoucherModel selected = findByCode(code);
		if(selected == null) {
			return null;
		}
		if(selected.isUsed()){
			throw new NotFoundException();
		}
		Date expiry = selected.getExpiresOn();
		if(expiry.getTime() < System.currentTimeMillis()){
			// Expired
			throw new NotFoundException();
		}
		selected.setUsed(true);

		return mongoService.save(selected);

	}


}
