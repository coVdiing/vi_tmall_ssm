package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.ProductImage;

public interface ProductImageService {
	// ��ʾ����ͼƬ
	String type_single = "type_single";
	// ��ʾ����ͼƬ
	String type_detail = "type_detail";

	void add(ProductImage productImage);

	void delete(int id);
	//����id��ȡ����ͼƬ
	ProductImage get(int id);
	//������ȡ��ƷͼƬ������һ�ֲ�Ʒ������ͼƬ
	//������ͼƬ��С���ڵ�ͼƬ��������Ҫ���������ж�
	List<ProductImage> list(int pid, String type);
}
