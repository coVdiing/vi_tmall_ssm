package com.vi.tmall.service;

import com.vi.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
	//增删改查一套
	void add(OrderItem orderItem);
	void delete(int id);
	void update(OrderItem orderItem);

	/**
	 * 获取填充过product对象的orderItem
	 * @param id
	 * @return
	 */
	OrderItem get(int id);

	/**
	 * 获取填充过product对象的orderItem集合
	 * @param oid
	 * @return
	 */
	List<OrderItem> list(int oid);

	/**在orderItem中填充product对象
	 * @param orderItem
	 */
	void fill(OrderItem orderItem);

	/**根据产品获取销量
	 * @param pid 产品id
	 * @return
	 */
	int getSaleCount(int pid);
	/**
	 * 根据用户id获取订单项集合
	 * @param uid 用户id
	 * @return 订单集合
	 */
	List<OrderItem> listByUser(int uid);
}
