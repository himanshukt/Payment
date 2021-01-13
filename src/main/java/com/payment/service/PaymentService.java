package com.payment.service;



import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Customer;
import com.payment.model.PaymentRequestInfo;
@Service
public class PaymentService {
	
	final static String Auth="cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";
	
	
	
	public static void createUser(Customer customer) throws JsonProcessingException {
		
		String URL="https://api.test.paysafe.com/paymenthub/v1/customers";
		 RestTemplate restTemp=new RestTemplate();
		 ObjectMapper om=new ObjectMapper();
		 String json=om.writeValueAsString(customer);
		 System.out.println("JSON IS: "+json);
		 HttpHeaders headers=new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		 headers.setBasicAuth(Auth);
		 
		 
		 HttpEntity <String> entity = new HttpEntity<String>(json,headers);
		 System.out.println(entity);
		 
		 String obj = restTemp.postForEntity(URL,entity,String.class).getBody();
		 System.out.println(obj);

	}
	
	public static String getSingleUseCustomerToken(String emailId) throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
		String token="";
		String url="https://api.test.paysafe.com/paymenthub/v1/customers/dbe1f835-f75e-4c74-8184-9eb4d41e1cdb/singleusecustomertokens";
		List list=Arrays.asList("CARD");
		String merchantRefNum="1234567";
		HashMap map=new HashMap();
		map.put("paymentTypes", list);
		map.put("merchantRefNum",merchantRefNum);
		ObjectMapper om=new ObjectMapper();
		String json=om.writeValueAsString(map);
		System.out.println(json);
		HttpHeaders headers=new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		 headers.setBasicAuth(Auth);
		 RestTemplate restTemp=new RestTemplate();
		 HttpEntity <String> entity = new HttpEntity<String>(json,headers);
		 System.out.println(entity);
		
		 String obj = restTemp.postForEntity(url,entity,String.class).getBody();
		 JSONParser parser = new JSONParser();
		 JSONObject object=(JSONObject) parser.parse(obj);
		 token=(String) object.get("singleUseCustomerToken");
		 return token;
	}
	
	public String makePayment( PaymentRequestInfo paymentRequestInfo){

        System.out.println( "In make payment" );

        // create an url for the payemnt api
        String url = "https://api.test.paysafe.com/paymenthub/v1/payments";
       
       
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		headers.setBasicAuth(Auth);
		paymentRequestInfo.setCustomerIp("127.0.0.1");
		
		int refNum=new Random().nextInt(100);
		paymentRequestInfo.setMerchantRefNum("123"+refNum);
        

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString="";
		try {
			jsonString = objectMapper.writeValueAsString(paymentRequestInfo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        HttpEntity< String > entity = new HttpEntity<String>( jsonString, headers );
        
        RestTemplate restTemplate=new RestTemplate();

         String responseEntity = restTemplate.postForEntity( url, entity,String.class ).getBody();
         System.out.println(responseEntity);
        return responseEntity;
    }
}
