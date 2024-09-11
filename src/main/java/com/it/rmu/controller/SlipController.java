package com.it.rmu.controller;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.it.rmu.model.ResponseModel;
import com.it.rmu.service.SlipService;

@RestController
@RequestMapping("/slip")
public class SlipController {

    @Autowired
    private SlipService slipService;
    
    @PostMapping(value =("/saveSlip/{ordersId}") , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseModel saveImage(@RequestParam("file") MultipartFile file, @PathVariable Integer ordersId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(slipService.saveImage(file, ordersId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

 
    @GetMapping("/getAllPayments")
    public ResponseModel getAllPayments() {
        ResponseModel response = new ResponseModel();
        
        try {
            response.setData(slipService.getAllPayments());
            response.setStatus("SUCCESS");
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage(e.getMessage());
        }
        
        return response;
    }
	@GetMapping("/getSlipImgByOrdersId")
	public ResponseModel getSlipImgByOrdersId(@RequestParam(name = "ordersId") Integer ordersId) {
		ResponseModel response = new ResponseModel();
		
		try {
			// service
			response.setData(slipService.getSlipImgByOrdersId(ordersId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseModel delete(@RequestParam(name = "profileImgId") Integer profileImgId) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			response.setData(slipService.delete(profileImgId));
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	@DeleteMapping(value =("/deleteImgByFileName"))
	public ResponseModel deleteImgByFileName(@RequestParam(name = "fileName") String fileName) {
		
		ResponseModel response = new ResponseModel();
		
		try {
			slipService.deleteImgByFileName(fileName);
			response.setData("SUCCESS");
			response.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	@GetMapping("/getImageByte")
	public ResponseEntity<byte[]> getImageByte(@RequestParam(name = "fileName") String fileName) throws IOException, DataFormatException {

		return ResponseEntity.ok(slipService.getImageByte(fileName));
	}
}