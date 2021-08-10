package com.iwantunlimited.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.iwantunlimited.model.VoucherModel;
import com.iwantunlimited.service.VoucherService;

import java.util.List;

@RestController
public class VoucherController {
	
	@Autowired
	VoucherService service;
	
	@GetMapping("/voucher/generate/{qty}/{amount}")
	public List<VoucherModel> generateCode(@PathVariable("qty") int number , @PathVariable("amount") double amount) {
			return service.create(number,amount);
		}
	
	@PostMapping("/voucher/verify")
	public VoucherModel verify(@RequestBody VoucherModel codeObject) {
		try {
			return service.findByCode(codeObject.getCode());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("voucher/redeem")
	public VoucherModel redeem(@RequestBody VoucherModel codeObject) {
		
			 VoucherModel model;
			try {
				model = service.redeem(codeObject.getCode());
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			 System.out.println("VoucherModel => "+ model);
			 return null ;
		
		
	}
}
	
