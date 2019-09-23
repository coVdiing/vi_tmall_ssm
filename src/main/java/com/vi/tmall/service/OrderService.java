package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Order;

public interface OrderService {
	String waitPay = "waitPay";
	String waitDelivery = "waitDelivery";
	String waitConfirm = "waitConfirm";
	String waitReview = "waitReview";
	String finish = "finish";
	String delete = "delete";
	
	//��ɾ���һ��
	void add(Order order);
	void delete(int id);
	void update(Order order);
	Order get(int id);
	List<Order> list();
	//����Ӧ��User��OrderItem����Order
	void fill(Order order);
}
