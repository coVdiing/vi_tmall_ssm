package com.vi.tmall.comparator;

import com.vi.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceAscComparator implements Comparator<Product> {
    /**
     * 按照价格从低到高排序
     *
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Product p1, Product p2) {
       return (int) (p1.getPromotePrice() - p2.getPromotePrice());
    }
}
