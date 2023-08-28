package com.endproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EndProductDao {
@Autowired
EndProductRepository epr;
	public String setEndProduct(List<EndProduct> x) {
		epr.saveAll(x);
		return "Success";
	}

}
