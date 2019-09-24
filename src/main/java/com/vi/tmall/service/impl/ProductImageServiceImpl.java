package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.ProductImageMapper;
import com.vi.tmall.pojo.ProductImage;
import com.vi.tmall.pojo.ProductImageExample;
import com.vi.tmall.service.ProductImageService;
@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	ProductImageMapper productImageMapper;
	
	@Override
	public void add(ProductImage productImage) {
		productImageMapper.insert(productImage);
	}

	@Override
	public void delete(int id) {
		productImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ProductImage get(int id) {
		return productImageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductImage> list(int pid, String type) {
		ProductImageExample example = new ProductImageExample();
		//根据类型和产品id条件查询
		example.createCriteria().andTypeEqualTo(type).andPidEqualTo(pid);
		//按照id将查询结果倒序
		example.setOrderByClause("id desc");
		return productImageMapper.selectByExample(example);
	}

}
