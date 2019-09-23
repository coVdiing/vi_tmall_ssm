package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Property;

public interface PropertyService {
	List<Property> list(int cid);
	void add(Property category);
	Property get(int id);
	void update(Property category);
	void delete(int id);
}
