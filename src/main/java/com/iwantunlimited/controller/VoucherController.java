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

@RestController
public class VoucherController {
	
	@Autowired
	VoucherService service;
	
	@GetMapping("/voucher/generate/{number}")
	public void generateCode(@PathVariable int number) {
			service.addVouchers(number);
		}
	
	@PostMapping("/voucher/verify")
	public VoucherModel verify(@RequestBody VoucherModel codeObject) {
		try {
			return service.verification(codeObject);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("voucher/redeem")
	public VoucherModel redeem(@RequestBody VoucherModel codeObject) {
		try {
			return service.redeem(codeObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
	
