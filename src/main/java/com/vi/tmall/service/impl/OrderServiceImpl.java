package com.vi.tmall.service.impl;

import java.util.List;

import com.vi.tmall.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.OrderMapper;
import com.vi.tmall.service.OrderItemService;
import com.vi.tmall.service.OrderService;
import com.vi.tmall.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order get(int id) {
		// 根据id查询订单
		Order order = orderMapper.selectByPrimaryKey(id);
		//填充订单对应的订单项集合
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
	 * 填充订单中的对应内容。total:总金额，totalNumber:商品总数，orderItems:订单项集合
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

	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName = "Exception")
	@Override
	public float add(Order order, List<OrderItem> orderItems) {
		add(order);
		float total = 0;
		if (false) {
			throw new RuntimeException();
		}
		for (OrderItem orderItem : orderItems) {
			orderItem.setOid(order.getId());
			orderItemService.update(orderItem);
			total+=orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
		}
		return total;
	}
}
