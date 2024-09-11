package com.it.rmu.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it.rmu.entity.SlipImgEntity;
import com.it.rmu.model.SlipResponseModel;
import com.it.rmu.repository.OrderRepository;
import com.it.rmu.repository.SlipImgRepository;
import com.it.rmu.utils.Constants;
import com.it.rmu.utils.ImgUtils;

import jakarta.transaction.Transactional;
@Service
public class SlipService {
	
	  @Autowired
	    OrderRepository ordersRepository;
	    
	    @Autowired
	    SlipImgRepository slipImgRepository;

	 @Transactional
	    public Integer delete(Integer slipImgId) throws IOException {
	        Integer response = null;
	        if (null != slipImgId) {
	            slipImgRepository.deleteByOrdersId(slipImgId);
	            this.removeImgAndDeleteFileImgByOrdersId(slipImgId);
	            response = slipImgId;
	        }
	        return response;
	    }
	
	  public List<SlipResponseModel> getSlipImgByOrdersId(Integer ordersId){
	        List<SlipResponseModel> response = null;
	        
	        List<SlipImgEntity> slipImgList = slipImgRepository.findByOrdersId(ordersId);
	        
	        if (null != slipImgList) {
	            response = new ArrayList<>();
	            for (SlipImgEntity slipImg : slipImgList) {
	                SlipResponseModel objectResponse = new SlipResponseModel();
	                objectResponse.setSlipImgId(slipImg.getId());
	                objectResponse.setOrdersId(slipImg.getOrdersId());
	                objectResponse.setSlipImgName(slipImg.getSlipImgName());
	                objectResponse.setSlipImgPath(slipImg.getSlipImgPath());
	                objectResponse.setSlipImgData(slipImg.getSlipImgData());
	                objectResponse.setStatus(slipImg.getStatus());
	                response.add(objectResponse);
	            }
	        }
	        
	        return response;
	    }
	    
	    @Transactional
	    public void removeImgAndDeleteFileImgByOrdersId(Integer ordersId) throws IOException {
	        List<SlipImgEntity> slipImgList = slipImgRepository.findByOrdersId(ordersId);
	        slipImgRepository.deleteAll(slipImgList);
	    }
	    
	    @Transactional
	    public void deleteImgByFileName(String fileName) throws IOException {
	        SlipImgEntity slipImg = slipImgRepository.findBySlipImgName(fileName);
	        slipImgRepository.delete(slipImg);
	    }
	    
	    public byte[] getImageByte(String fileName) throws IOException, DataFormatException {
	        SlipImgEntity slipImg = slipImgRepository.findBySlipImgName(fileName);
	        
	        if (null != slipImg) {
	            return ImgUtils.decompressImage(slipImg.getSlipImgData());
	        }

	        return null;
	    }
	    @Transactional
	    public Integer saveImage(MultipartFile file, Integer ordersId) throws IOException {
	        Integer response = null;
	        if (null != file && null != ordersId) {
	            SlipImgEntity slipImg = new SlipImgEntity();
	            String preFixNameFile = ImgUtils.genaratePrefixFile();
	            String genarateFileName = ImgUtils.genarateFileName() + ImgUtils.subStrFileName(file.getOriginalFilename());
	            String fileName = ImgUtils.concatStr(preFixNameFile, genarateFileName);
	            slipImg.setSlipImgName(fileName);
	            slipImg.setSlipImgPath(ImgUtils.getPathInput());
	            slipImg.setOrdersId(ordersId);
	            slipImg.setSlipImgData(ImgUtils.compressImage(file.getBytes()));
	            slipImg.setStatus("1");
	            slipImg.setCreateBy("Joey");
	            slipImg.setCreateDate(new Date());
	            slipImg.setUpdateBy("Joey Update");
	            slipImg.setUpdateDate(new Date());
	            slipImg = slipImgRepository.save(slipImg);
	            response = slipImg.getId();
	            
//	            ImgUtils.saveFile(file, fileName, Constants.PATH_TYPE_INPUT);
	        }
	        return response;
	    }
	  @Transactional
	    public void deleteImgSever(String fileName) throws IOException {
	        if (null != fileName) {
	            ImgUtils.deleteFile(fileName, Constants.PATH_TYPE_INPUT);
	        }
	    }
	  public List<SlipResponseModel> getAllPayments() {
	        List<SlipResponseModel> response = new ArrayList<>();
	        List<SlipImgEntity> paymentImgList = slipImgRepository.findAll();
	        if (null != paymentImgList) {
	            for (SlipImgEntity paymentImg : paymentImgList) {
	            	SlipResponseModel objectResponse = new SlipResponseModel();
	                objectResponse.setSlipImgId(paymentImg.getId());
	                objectResponse.setOrdersId(paymentImg.getOrdersId());
	                objectResponse.setSlipImgName(paymentImg.getSlipImgName());
	                objectResponse.setSlipImgPath(paymentImg.getSlipImgPath());
	                objectResponse.setSlipImgData(paymentImg.getSlipImgData());
	                objectResponse.setStatus(paymentImg.getStatus());
	                response.add(objectResponse);
	            }
	        }
	        return response;
	    }
}