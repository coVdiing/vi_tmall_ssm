package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.OrderItem;

public interface OrderItemService {
	//增删查改一套
	void add(OrderItem orderItem);
	void delete(int id);
	void update(OrderItem orderItem);
	OrderItem get(int id);
	List<OrderItem> list(int oid);
	//填充OrderItem中的商品
	void fill(OrderItem orderItem);
}
