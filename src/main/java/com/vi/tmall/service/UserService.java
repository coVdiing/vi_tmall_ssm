package com.vi.tmall.service;

import java.util.List;

import com.vi.tmall.pojo.User;

public interface UserService {
	List<User> list();
	User get(int id);
	void add(User user);
	void update(User user);
	void delete(int id);
	boolean isExist(String name);
}
