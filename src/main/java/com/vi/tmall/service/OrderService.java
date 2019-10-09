package com.vi.tmall.service;

import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.OrderItem;

import java.util.List;

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

    /**
     * 根据id查询订单（带订单项集合)
     * @param id order id
     * @return order对象,包括total:总金额，totalNumber:商品总数，orderItems:订单项集合
     */
    Order get(int id);

    List<Order> list();

    //在Order中填充OrderItem对象集合
    void fill(Order order);

    //订单结算时，新增订单
    float add(Order order, List<OrderItem> orderItems);

    //根据筛选条件查询订单
    List<Order> list(int uid, String excludeStatus);
}
