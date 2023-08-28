package com.endproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EndProductService {
@Autowired
EndProductDao epd;
	public String setEndProduct(List<EndProduct> x) {
		return epd.setEndProduct(x);	
	}

}
