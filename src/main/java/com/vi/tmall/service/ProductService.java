package com.vi.tmall.service;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    //增删改查
    void add(Product product);

    void delete(int id);

    void update(Product product);

    /**
     * 填充过销量和评论数量，分类对象，图片对象的product
     * @param id
     * @return
     */
    Product get(int id);

    /**
     * 填充过销量和评论数量，分类对象，图片对象的product集合
     * @param cid
     * @return
     */
    List<Product> list(int cid);

    void setFirstProductImage(Product p);

    //新增三个方法，给Category设置对应的product
    void fill(Category category);

    void fill(List<Category> categories);

    void fillByRow(List<Category> categories);

    //为产品设置销量和评论数量
    void setSaleAndReviewNumber(Product product);

    void setSaleAndReviewNumber(List<Product> products);

    List<Product> search(String keyword);
}
