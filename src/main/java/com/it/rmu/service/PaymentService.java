package com.it.rmu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.rmu.entity.PaymentEntity;
import com.it.rmu.model.PaymentRequestModel;
import com.it.rmu.model.PaymentResponseModel;
import com.it.rmu.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public PaymentResponseModel getById(Integer paymentId) {

		PaymentResponseModel response = null;

		Optional< PaymentEntity> entity = paymentRepository.findById(paymentId);
		

		if (entity.isPresent()) {
			PaymentEntity payment = entity.get();
			response = new PaymentResponseModel();

			response.setPaymentId(payment.getId());
			response.setUserId(payment.getUserId());
			response.setOrderId(payment.getOrderId());
			response.setPaymentAmount(payment.getPaymentAmount());
			response.setPaymentMethod(payment.getPaymentMethod());
			response.setStatus(payment.getStatus());
		}

		return response;
	}

	public List<PaymentResponseModel> getAll() {

		List<PaymentResponseModel> response = null;

		List<PaymentEntity> entitys = paymentRepository.findAll();

		if (null != entitys) {
			response = new ArrayList<>();
			for (PaymentEntity payment : entitys) {

				PaymentResponseModel object = new PaymentResponseModel();

				object.setPaymentId(payment.getId());
				object.setUserId(payment.getUserId());
				object.setOrderId(payment.getOrderId());
				object.setPaymentAmount(payment.getPaymentAmount());
				object.setPaymentMethod(payment.getPaymentMethod());
				object.setStatus(payment.getStatus());

				response.add(object);
			}

		}
		return response;

	}

	@Transactional
	public Integer save(PaymentRequestModel paymentRequest) throws IOException {

		Integer response = null;

		if (null != paymentRequest) {
			PaymentEntity paymentEntity = new PaymentEntity();
			paymentEntity.setUserId(paymentRequest.getUserId());
			paymentEntity.setOrderId(paymentRequest.getOrderId());
			paymentEntity.setPaymentAmount(paymentRequest.getPaymentAmount());
			paymentEntity.setPaymentDate(new Date());
			paymentEntity.setPaymentMethod(paymentRequest.getPaymentMethod());
			paymentEntity.setStatus(paymentRequest.getStatus());

			paymentEntity = paymentRepository.save(paymentEntity);
			response = paymentEntity.getId();

		}
		return response;
	}

	@Transactional
	public Integer update(PaymentRequestModel paymentRequest, Integer paymentId) throws IOException {
		Integer response = null;

		Optional<PaymentEntity> paymentEntity = paymentRepository.findById(paymentId);
		if (paymentEntity.isPresent()) {
			PaymentEntity payment = paymentEntity.get();
			payment.setUserId(null != paymentRequest.getUserId() ? paymentRequest.getUserId() : payment.getUserId());
			payment.setOrderId(null != paymentRequest.getOrderId() ? paymentRequest.getOrderId() : payment.getOrderId());
			payment.setPaymentAmount(null != paymentRequest.getPaymentAmount() ? paymentRequest.getPaymentAmount(): payment.getPaymentAmount());
			payment.setPaymentMethod(null != paymentRequest.getPaymentMethod() ? paymentRequest.getPaymentMethod(): payment.getPaymentMethod());
			payment.setStatus(null != paymentRequest.getStatus() ? paymentRequest.getStatus() : payment.getStatus());
			payment.setPaymentDate(new Date());
			
			payment = paymentRepository.save(payment);

			response = payment.getId();

		}

		return response;

	}

	@Transactional
	public Integer delete(Integer paymentId) throws IOException {
		Integer response = null;
		if (null != paymentId) {
			paymentRepository.deleteById(paymentId);
			response = paymentId;
		}
		return response;
	}

}
