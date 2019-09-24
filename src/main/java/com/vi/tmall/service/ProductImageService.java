package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.ProductImage;

public interface ProductImageService {
    // 单个图片类型
    String type_single = "type_single";
    // 详情图片类型
    String type_detail = "type_detail";

    void add(ProductImage productImage);

    void delete(int id);

    ProductImage get(int id);

    List<ProductImage> list(int pid, String type);
}
