package com.vi.tmall.comparator;

import com.vi.tmall.pojo.Product;

import java.util.Comparator;


public class ProductDateComparator implements Comparator<Product> {
    /**
     * 按照创建日期的先后顺序进行倒序排序，即近期排在前面。
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
}
