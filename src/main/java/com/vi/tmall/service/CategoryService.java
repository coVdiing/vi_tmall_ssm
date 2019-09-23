package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.Category;

public interface CategoryService {
	List<Category> list();
	void add(Category category);
	Category get(int id);
	void update(Category category);
	void delete(int id);
}
