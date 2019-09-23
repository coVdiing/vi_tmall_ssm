package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.ProductMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductExample;
import com.vi.tmall.pojo.ProductImage;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductImageService;
import com.vi.tmall.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductImageService ProductImageService;
	@Autowired
	CategoryService categoryService;
	
	@Override
	public void add(Product product) {
		productMapper.insert(product);
	}

	@Override
	public void delete(int id) {
		productMapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void update(Product product) {
		productMapper.updateByPrimaryKey(product);
		
	}

	@Override
	public Product get(int id) {
		Product product = productMapper.selectByPrimaryKey(id);
		int cid = product.getCid();
		Category category = categoryService.get(cid);
		System.out.println("cid===>"+cid);//调试代码
		System.out.println("category====>"+category.getName());//调试代码
		product.setCategory(category);
		setFirstProductImage(product);
		return product;
	}

	@Override
	public List<Product> list(int cid) {
		//根据Category id进行查询
		ProductExample example = new ProductExample();
		example.createCriteria().andCidEqualTo(cid);
		//按照id降序排列
		example.setOrderByClause("id desc");
		Category category = categoryService.get(cid);
		
		List<Product> list = productMapper.selectByExample(example);
		//在找到了对应的数据之后，给他们加上对应的图片
		setFirstProductImage(list);
		for(Product product : list)
			product.setCategory(category);
		return list;
	}
	
	/**
	 * 给product对象赋上对应的ProductImage
	 */
	@Override
	public void setFirstProductImage(Product product) {
		List<ProductImage> plist =
				ProductImageService.list(product.getId(), ProductImageService.type_single);
		if(!plist.isEmpty()){
			//如果结果集非空，取出其中第一张图片，设置为product的productImage对象
			product.setFirstProductImage(plist.get(0));
		}
	}
	
	public void setFirstProductImage(List<Product> list) {
		for(Product product : list)
			setFirstProductImage(product);
	}
}
