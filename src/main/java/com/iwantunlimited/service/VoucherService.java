package com.iwantunlimited.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.iwantunlimited.model.VoucherModel;

@Component
public class VoucherService {
	

	@Autowired
	protected MongoTemplate template;
	
	private static final Class<VoucherModel> model = VoucherModel.class;
	
	public String createRandomCode(int codeLength){   
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < codeLength; i++) {
			char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		    }
			String output = sb.toString();
		    System.out.println(output);
		    return output ;
		    }
	
	public Date setDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
		
	public void addVouchers(int number) {
		for(int i = 1; i <= number; i++) {
			String code = createRandomCode(8);
			VoucherModel model = new VoucherModel();
			model.setCode(code);
			model.setUser(false);
			model.setDate(setDate());
			template.insert(model);
		}
	}
		
	public VoucherModel verification(VoucherModel codeObject) throws NotFoundException {
		String code = codeObject.getCode();
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		VoucherModel selected = template.findOne(query, model);
		if (selected == null) {
			throw new NotFoundException();
		}
		else {
			return template.findOne(query, model);
		}
	}

	public VoucherModel redeem(VoucherModel codeObject){
		String code = codeObject.getCode();
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		VoucherModel selected = template.findOne(query, model);
		if(selected == null) {
			return null;
		}
		else if(selected.getUser() == true) {
			return selected;
		}
		else {
			selected.setUser(true);
			template.save(selected);
			return selected;
		}
		
	}

}
