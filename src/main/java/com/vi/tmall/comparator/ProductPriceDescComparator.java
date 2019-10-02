package com.vi.tmall.comparator;

import com.vi.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceDescComparator implements Comparator<Product> {

    /**
     * 根据价格降序排列
     *
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Product p1, Product p2) {
        return (int) (p2.getPromotePrice() - p1.getPromotePrice());
    }
}
