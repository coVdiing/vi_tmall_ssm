package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.UserMapper;
import com.vi.tmall.pojo.User;
import com.vi.tmall.pojo.UserExample;
import com.vi.tmall.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<User> list() {
		UserExample userExample = new UserExample();
		userExample.setOrderByClause("id desc");
		return userMapper.selectByExample(userExample);
	}

	@Override
	public void add(User user) {
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void delete(int id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean isExist(String name) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(name);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty())
			return true;
		return false;
	}

	/**
	 * 根据用户名和密码查找User
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public User get(String name, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> users = userMapper.selectByExample(userExample);
		return users.size()==0?null:users.get(0);
	}

	@Override
	public User get(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
}
