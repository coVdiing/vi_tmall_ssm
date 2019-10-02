package com.vi.tmall.comparator;

import com.vi.tmall.pojo.Product;

import java.util.Comparator;

public class ProductReviewComparator implements Comparator<Product> {

    /**
     * 根据评论数量从高到低排列
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() - p1.getReviewCount();
    }
}
