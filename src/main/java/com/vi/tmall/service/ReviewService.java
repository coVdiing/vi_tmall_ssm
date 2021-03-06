package com.vi.tmall.service;

import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    void add(Review review);
    void delete(int id);
    void update(Review review);
    Review get(int id);
    List<Review> list(int pid);
    int getCount(int pid);

    /**
     * 增加评论，同时修改订单状态，提交到数据库
     * @param review
     * @param order
     */
    void add(Review review, Order order);
}
