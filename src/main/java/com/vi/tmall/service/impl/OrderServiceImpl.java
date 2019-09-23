package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.OrderMapper;
import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.OrderExample;
import com.vi.tmall.pojo.OrderItem;
import com.vi.tmall.pojo.User;
import com.vi.tmall.service.OrderItemService;
import com.vi.tmall.service.OrderService;
import com.vi.tmall.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	UserService userService;

	@Override
	public void add(Order order) {
		orderMapper.insert(order);
	}

	@Override
	public void delete(int id) {
		orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Order order) {
		// 一定要用这个selective的更新方法，很重要!!!
		orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order get(int id) {
		// 从数据库获取对应的order对象
		Order order = orderMapper.selectByPrimaryKey(id);
		fill(order);
		return order;
	}

	@Override
	public List<Order> list() {
		OrderExample orderExample = new OrderExample();
		orderExample.setOrderByClause("id desc");
		List<Order> orders = orderMapper.selectByExample(orderExample);
		for(Order order : orders)
			fill(order);
		return orders;
	}

	/**
	 * order对象中没有user,orderItems,total,totalNumber,我们在下面把这些数据加进去，方便之后控制层的操作
	 */
	@Override
	public void fill(Order order) {
		User user = userService.get(order.getUid());
		order.setUser(user);
		List<OrderItem> orderItems = orderItemService.list(order.getId());
		order.setOrderItems(orderItems);
		int total = 0;
		int totalNumber = 0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
			totalNumber += orderItem.getNumber();
		}
		order.setTotal(total);
		order.setTotalNumber(totalNumber);
	}

}
