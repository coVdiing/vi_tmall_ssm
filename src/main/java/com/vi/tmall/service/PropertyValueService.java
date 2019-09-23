package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.PropertyValue;

public interface PropertyValueService {
	void init(Product product);
	PropertyValue get(int pid,int ptid);
	List<PropertyValue> list(int pid);
	void update(PropertyValue propertyValue);
}
