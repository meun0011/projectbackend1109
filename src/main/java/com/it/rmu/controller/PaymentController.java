package com.it.rmu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.rmu.model.PaymentRequestModel;
import com.it.rmu.model.ResponseModel;
import com.it.rmu.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/getById")
	public ResponseModel getById(@RequestParam(name = "paymentId") Integer paymentId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.getById(paymentId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	
	@GetMapping("/getAll")
	public ResponseModel getAll() {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.getAll());
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PostMapping("/save")
	public ResponseModel save(@RequestBody PaymentRequestModel paymentRequest) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.save(paymentRequest));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@PutMapping("/update/{paymentId}")
	public ResponseModel update(@RequestBody PaymentRequestModel paymentRequest, @PathVariable Integer paymentId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(paymentService.update(paymentRequest, paymentId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam(name = "paymentId") Integer paymentId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(paymentService.delete(paymentId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
}
