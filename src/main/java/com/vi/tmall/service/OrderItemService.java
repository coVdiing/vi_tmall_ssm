package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.OrderItem;

public interface OrderItemService {
	//增删改查一套
	void add(OrderItem orderItem);
	void delete(int id);
	void update(OrderItem orderItem);
	OrderItem get(int id);
	List<OrderItem> list(int oid);
	//在orderItem中填充product对象
	void fill(OrderItem orderItem);
	//根据产品获取销量
	int getSaleCount(int pid);
}
