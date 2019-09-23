package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.OrderItem;

public interface OrderItemService {
	//��ɾ���һ��
	void add(OrderItem orderItem);
	void delete(int id);
	void update(OrderItem orderItem);
	OrderItem get(int id);
	List<OrderItem> list(int oid);
	//���OrderItem�е���Ʒ
	void fill(OrderItem orderItem);
}
