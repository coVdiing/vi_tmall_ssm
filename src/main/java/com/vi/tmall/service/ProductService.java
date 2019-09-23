package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Product;

public interface ProductService {
	//��ɾ�Ĳ�
	void add(Product product);
	void delete(int id);
	void update(Product product);
	Product get(int id);
	List<Product> list(int cid);
	void setFirstProductImage(Product p);
}
