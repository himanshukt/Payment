package com.payment.controller;


import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment.model.PaymentRequestInfo;
import com.payment.service.PaymentService;


@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping("/")
	@ResponseBody
    public ModelAndView getCheckoutForm(){
		 	ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("index");
		    return modelAndView;
    }
	
	@RequestMapping("/SingleUseCustomerToken/{emailId}")
	@ResponseBody
	public JSONObject getSingleUseCustomerToken(@PathVariable String emailId) {
		String tval="";
		try {
			tval= paymentService.getSingleUseCustomerToken(emailId);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject object=new JSONObject();
		object.put("singleUseCustomerToken",tval);
		JSONObject rObject=new JSONObject();
		object.put("merchantRefNum","123456");
		rObject.put("data",object);
		rObject.put("status", "OK");
		return rObject;
	}
	
	@PostMapping( "/MakePayment/{emailId}" )
    public JSONObject makePayment(@PathVariable String emailId,@RequestBody PaymentRequestInfo paymentRequestInfo){

       JSONObject reObject=new JSONObject();
       reObject.put("status","OK");
       reObject.put("message","Payment Done Successfully");
       reObject.put("data",paymentService.makePayment(paymentRequestInfo));
        return reObject;
    }

}
