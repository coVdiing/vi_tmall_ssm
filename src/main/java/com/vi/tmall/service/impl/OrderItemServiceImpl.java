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

	/**
	 * 获取对应商品的销量
	 * @param pid 商品id
	 * @return
	 */
	@Override
	public int getSaleCount(int pid) {
		OrderItemExample orderItemExample = new OrderItemExample();
		orderItemExample.createCriteria().andPidEqualTo(pid);
		List<OrderItem> orderitems = orderItemMapper.selectByExample(orderItemExample);
		int salecount = 0;
		for(OrderItem orderItem : orderitems)
			salecount += orderItem.getNumber();
		return salecount;
	}

	/**
	 * 根据用户id获取对应的订单项集合
	 * @param uid
	 * @return
	 */
	public List<OrderItem> listByUser(int uid) {
		OrderItemExample orderItemExample = new OrderItemExample();
		//查出对应用户id的订单项集合，并且是没有生成订单的 oid is null
		orderItemExample.createCriteria().andUidEqualTo(uid).andOidIsNull();
		orderItemExample.setOrderByClause("id desc");
		List<OrderItem> result = orderItemMapper.selectByExample(orderItemExample);
		for (OrderItem orderItem : result) {
			fill(orderItem);
		}
		return result;
	}
}
