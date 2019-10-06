package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.OrderItem;

public interface OrderService {
	String waitPay = "waitPay";
	String waitDelivery = "waitDelivery";
	String waitConfirm = "waitConfirm";
	String waitReview = "waitReview";
	String finish = "finish";
	String delete = "delete";
	
	//增删查改一套
	void add(Order order);
	void delete(int id);
	void update(Order order);
	Order get(int id);
	List<Order> list();
	//在Order中填充OrderItem对象集合
	void fill(Order order);
	//订单结算时，新增订单
	float add(Order order, List<OrderItem> orderItems);
}
