package com.endproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EndProductController {
@Autowired
RestTemplate rt;
@Autowired
EndProductService eps;
@GetMapping("/getUpper")
public String getUpperString() {
	String url="http://localhost:8080/getString";
	ResponseEntity<String> response=rt.exchange(url,HttpMethod.GET,null,String.class);
	String s=response.getBody();
	return s.toUpperCase();
}
@GetMapping("/getEndProduct")
public List<EndProduct> getEndProduct() {
	String url1="http://localhost:8080/getAll";
	String url2="http://localhost:8081/getDisByHsn/";
	ResponseEntity<List<EndProduct>> r1= rt.exchange(url1,HttpMethod.GET, null, new ParameterizedTypeReference<List<EndProduct>>() {});
	List<EndProduct> ep=r1.getBody();
	ep.forEach(x-> {
		int hsn=x.getHsn();
		ResponseEntity<Integer> r2=rt.exchange(url2+hsn,HttpMethod.GET, null,Integer.class);
		int d=r2.getBody();
		x.setPrice(x.getPrice()-x.getPrice()*d/100);
	});
	return ep;
}
@PostMapping("/setEndProduct")
public String setEndProduct() {
	List<EndProduct> x=getEndProduct();
	return eps.setEndProduct(x);
}
}
