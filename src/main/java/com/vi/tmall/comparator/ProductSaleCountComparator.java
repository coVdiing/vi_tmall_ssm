package com.vi.tmall.comparator;

import com.vi.tmall.pojo.Product;

import java.util.Comparator;

public class ProductSaleCountComparator implements Comparator<Product> {

    /**
     * 按照销量从高到低降序排列
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
}
