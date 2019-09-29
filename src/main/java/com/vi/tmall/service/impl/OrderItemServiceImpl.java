package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.OrderItemMapper;
import com.vi.tmall.pojo.OrderItem;
import com.vi.tmall.pojo.OrderItemExample;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.service.OrderItemService;
import com.vi.tmall.service.ProductService;
@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired 
	OrderItemMapper orderItemMapper;
	@Autowired
	ProductService productService;
	
	@Override
	public void add(OrderItem orderItem) {
		orderItemMapper.insert(orderItem);
	}

	@Override
	public void delete(int id) {
		orderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemMapper.updateByPrimaryKeySelective(orderItem);
	}

	@Override
	public OrderItem get(int id) {
		OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
		fill(orderItem);
		return orderItem;
	}

	@Override
	public List<OrderItem> list(int oid) {
		OrderItemExample orderItemExample = new OrderItemExample();
		orderItemExample.createCriteria().andOidEqualTo(oid);
		orderItemExample.setOrderByClause("id desc");
		List<OrderItem> list = orderItemMapper.selectByExample(orderItemExample);
		for(OrderItem orderItem : list)
			fill(orderItem);
		return list;
	}
	
	/**
	 * 填充订单项中对应的product对象
	 */
	@Override
	public void fill(OrderItem orderItem) {
		Product product = productService.get(orderItem.getPid());
		orderItem.setProduct(product);
	}

}
