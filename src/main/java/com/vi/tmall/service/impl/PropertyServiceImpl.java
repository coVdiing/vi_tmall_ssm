package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.CategoryMapper;
import com.vi.tmall.mapper.PropertyMapper;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.pojo.PropertyExample;
import com.vi.tmall.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService{
	@Autowired
	PropertyMapper propertyMapper;
	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public List<Property> list(int cid) {
		//����Category id�����Ҷ�Ӧ��property
		PropertyExample example = new PropertyExample();
		//��ѯ����
		example.createCriteria().andCidEqualTo(cid);
		//����id����
		example.setOrderByClause("id desc");
		List<Property> props = propertyMapper.selectByExample(example);
		return props;
	}

	@Override
	public void add(Property category) {
		propertyMapper.insertSelective(category);
	}

	@Override
	public Property get(int id) {
		return propertyMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Property category) {
		propertyMapper.updateByPrimaryKey(category);
	}

	@Override
	public void delete(int id) {
		propertyMapper.deleteByPrimaryKey(id);
	}

}
