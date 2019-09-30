package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;

public interface ProductService {
    //增删改查
    void add(Product product);

    void delete(int id);

    void update(Product product);

    Product get(int id);

    List<Product> list(int cid);

    void setFirstProductImage(Product p);

    //新增三个方法，给Category设置对应的product
    void fill(Category category);

    void fill(List<Category> categories);

    void fillByRow(List<Category> categories);

    //为产品设置销量和评论数量
    void setSaleAndReviewNumber(Product product);

    void setSaleAndReviewNumber(List<Product> products);
}
