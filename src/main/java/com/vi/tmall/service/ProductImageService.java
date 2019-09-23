package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.ProductImage;

public interface ProductImageService {
	// 表示单个图片
	String type_single = "type_single";
	// 表示详情图片
	String type_detail = "type_detail";

	void add(ProductImage productImage);

	void delete(int id);
	//根据id获取单张图片
	ProductImage get(int id);
	//批量获取产品图片，由于一种产品有两类图片
	//，详情图片和小窗口的图片，所以需要根据类型判断
	List<ProductImage> list(int pid, String type);
}
