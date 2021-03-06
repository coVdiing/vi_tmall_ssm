package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.ReviewMapper;
import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.Review;
import com.vi.tmall.pojo.ReviewExample;
import com.vi.tmall.pojo.User;
import com.vi.tmall.service.OrderService;
import com.vi.tmall.service.ReviewService;
import com.vi.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    /**
     * 给review对象设置对应的user对象，用于评论页
     * @param pid 对应的product id
     * @return
     */
    @Override
    public List<Review> list(int pid) {
        ReviewExample reviewExample = new ReviewExample();
        reviewExample.createCriteria().andPidEqualTo(pid);
        List<Review> reviews = reviewMapper.selectByExample(reviewExample);
       //为评论设置对应的user对象
        setUser(reviews);
        return reviews;
    }

    private void setUser(List<Review> reviews) {
        for(Review review : reviews)
            setUser(review);
    }


    private void setUser(Review review) {
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackForClassName = "Exception")
    @Override
    public void add(Review review, Order order) {
        orderService.update(order);
        if(false) {
            throw new RuntimeException();
        }
        reviewMapper.insert(review);
    }
}
