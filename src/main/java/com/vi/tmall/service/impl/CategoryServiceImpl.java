package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.CategoryMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.CategoryExample;
import com.vi.tmall.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;

	public List<Category> list() {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("id desc");
		return categoryMapper.selectByExample(example);
	}
	
	public void add(Category category) {
		categoryMapper.insertSelective(category);
	}

	public Category get(int id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	public void update(Category category) {
		categoryMapper.updateByPrimaryKey(category);
	}

	public void delete(int id) {
		categoryMapper.deleteByPrimaryKey(id);
	}
}
